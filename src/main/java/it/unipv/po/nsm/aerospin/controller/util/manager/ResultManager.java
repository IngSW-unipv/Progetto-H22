package controller.util.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import model.Factory;
import model.Session;
import model.booking.Fares;
import model.booking.ticket.Ticket;
import model.booking.ticket.TicketMail;
import model.exception.DBException;
import model.exception.NoMatchException;
import model.persistence.CachedFlights;
import model.persistence.entity.Booking;
import model.persistence.entity.Flight;
import model.persistence.entity.Passenger;
import model.persistence.service.BookingService;
import model.persistence.service.FlightService;
import model.persistence.service.PassengerService;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Classe che si occupa di gestire alcune logiche del ResultController, abbassando l'accoppiamento
 *
 * @author GruppoNoSuchMethod
 */
public class ResultManager {
    private final FlightService service = new FlightService();
    private final PassengerService passengerService = new PassengerService();
    private final BookingService bookingService = new BookingService();
    private List<Flight> results = CachedFlights.getInstance().findAll();

    private static final Pattern VALID_NAME_REGEX =
            Pattern.compile("^([a-zA-Z]){2,15}(?:[\\s]+[a-zA-Z]+)*$");

    /**
     * Metodo che restituisce i voli disponibili per la tratta e la data selezionate
     *
     * @param s1 Aeroporto di partenza
     * @param s2 Aeroporto di arrivo
     * @param s3 Data del volo
     * @return Lista di voli disponibili
     * @throws NoMatchException Segnala che non ha trovato alcun volo disponibile
     */
    public ObservableList<Flight>  getList(String s1, String s2, Date s3) throws NoMatchException {
        List<Flight> departures = results.stream()
                .filter(o -> o.getRouteById().getAirportDep().equalsString(s1))
                .filter(o -> o.getRouteById().getAirportArr().equalsString(s2))
                .filter(o -> o.getScheduledDate().equals(s3))
                .filter(o -> o.getSeats() > 0)
                .distinct()
                .collect(Collectors.toList());
        if(departures.size() == 0){
            throw new NoMatchException("Not Matched!\n");
        } else {
            return FXCollections.observableArrayList(departures);
        }
    }
    public void reloadFlight(){
        Task<Void> task = new Task<>() {
            @Override
            public Void call(){
                CachedFlights.getInstance().clearCache();
                results = CachedFlights.getInstance().findAll();
                return null;
            }
        };

        new Thread(task).start();

    }

    /**
     * Metodo che verifica se l'età inserita è minore di 16
     *
     * @param birthDate Data di nascita
     * @return true se sono meno di 16 anni, false altrimenti
     */
    public boolean isMinor(LocalDate birthDate){
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        return age.getYears() < 16;
    }

    /**
     * Metodo che permette di selezionare solo date di nascita passate
     *
     * @return Callback Object per il DatePicker
     */
    public Callback<DatePicker, DateCell> ageRange() {
        return new Callback<>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        LocalDate today = LocalDate.now().minusDays(1);
                        setDisable(empty || date.isAfter(today));
                    }
                };
            }
        };
    }

    /**
     * Metodo che verifica gli input con il REGEX dichiarato valid_name
     *
     * @param name Nome
     * @param surname Cognome
     * @throws IllegalArgumentException Segnala un errore nelle informazioni fornite dal cliente
     */
    public void fieldsCheck(String name, String surname) throws IllegalArgumentException {
        Matcher matcher1 = VALID_NAME_REGEX.matcher(name);
        Matcher matcher2 = VALID_NAME_REGEX.matcher(surname);
        if(!(matcher1.find() && matcher2.find())) {
                throw new IllegalArgumentException();
        }
    }

    /**
     * Metodo che carica il passeggero e la prenotazione in DB,
     * invia l'email della prenotazione e aggiorna il volo prenotato
     *
     * @param p Passeggero
     * @param fare Tariffa di viaggio selezionata
     * @param flight Volo selezionato
     * @throws RuntimeException Segnala un errore durante l'esecuzione del processo
     */
    public void fetchOrder(Passenger p, Fares fare, Flight flight) throws RuntimeException {
        passengerService.persist(p);

        Booking booking = new Booking();
        booking.setPassengerId(p.getId());
        booking.setFlightId(flight.getId());
        booking.setFare(fare);
        booking.setCardDetails(Integer.parseInt(Session.getInstance().getCardNumber()));
        booking.setOrderDate(new Date(System.currentTimeMillis()));
        booking.setPrice(flight.getPrice() * fare.getPriceM());
        booking.setPassengerById(p);
        booking.setFlightById(flight);
        bookingService.persist(booking);
        try {
                sendTicket(booking);
        } catch (IOException e) {
                e.printStackTrace();
        }
        bookSeat(booking.getFlightById());
    }

    /**
     * Metodo che effettua l'invio email del Ticket generato con alcune informazioni
     *
     * @param booking Prenotazione effettuata
     * @throws IOException Segnala che si è verificato un errore durante la generazione del Ticket
     * @throws RuntimeException Segnala un errore durante l'esecuzione del processo
     */
    public void sendTicket(Booking booking) throws IOException, RuntimeException {
        Ticket ticket = new Ticket(booking);
        TicketMail mail = new TicketMail();
        mail.setSubject("Il Tuo Biglietto [" + "APN" + booking.getId() + "]");
        mail.setText("Grazie " + booking.getPassengerById().getName()
                + " per averci scelto!\nIn allegato trovi il tuo biglietto\nA presto!");
        mail.send(booking.getPassengerById().getUserById().getEmail(), ticket.getPath());
    }

    /**
     * Metodo che, una volta completata la prenotazione, riduce i posti disponibili sul volo selezionato
     *
     * @param flight Volo prenotato
     */
    public void bookSeat(Flight flight) {
        flight.setSeats(flight.getSeats()-1);
        service.update(flight);
    }
}
