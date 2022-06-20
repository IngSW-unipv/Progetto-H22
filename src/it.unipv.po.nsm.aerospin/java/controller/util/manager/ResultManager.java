package controller.util.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import model.Factory;
import model.booking.Fares;
import model.booking.ticket.Ticket;
import model.booking.ticket.TicketMail;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Classe Manager che si occupa di gestire la logica dell'applicativo, relativa al Result.
 *
 * @author GruppoNoSuchMethod
 */
public class ResultManager {
    private final FlightService service = new FlightService();
    private final PassengerService passengerService = new PassengerService();
    private final BookingService bookingService = new BookingService();
    private final List<Flight> results = CachedFlights.getInstance().findAll();

    private static final Pattern VALID_NAME_REGEX =
            Pattern.compile("^[a-zA-z]{2,15}$");

    /**
     * Metodo che rende visibili i voli disponibili per tratta e per disponibilità di posti.
     *
     * @param s1 Aeroporto di partenza.
     * @param s2 Aeroporto di arrivo.
     * @param s3 Data del volo.
     * @return Vengono fornite le partenze disponibili.
     * @throws NoMatchException Segnala se il confronto non è andato a buon fine.
     */
    public ObservableList<Flight> getList(String s1, String s2, Date s3) throws NoMatchException {
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

    /*  Controllo se l'età selezionata è > 16
     *  e permetto di selezionare solo date passate
     *  come data di nascita
     */
    /**
     * Metodo che fornita in ingresso la data di nascita restituisce true se l'età è maggiore di 16.
     *
     * @param birthDate Data di nascita del cliente.
     * @return true se il cliente ha più di 16 anni, false altrimenti.
     */
    public boolean isMinor(LocalDate birthDate){
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        return age.getYears() < 16;
    }

    /**
     * Metodo che permette di selezionare solo date di nascita passate.
     *
     * @return Oggetto Callback relativo alla GUI.
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
     * Metodo per la verifica tramite REGEX delle informazioni fornite dal cliente.
     *
     * @param name Nome.
     * @param surname Cognome.
     * @throws IllegalArgumentException Segnala un errore nelle informazioni fornite dal cliente.
     */
    public void fieldsCheck(String name, String surname) throws IllegalArgumentException {
        Matcher matcher1 = VALID_NAME_REGEX.matcher(name);
        Matcher matcher2 = VALID_NAME_REGEX.matcher(surname);
        if(!(matcher1.find() && matcher2.find())) {
                throw new IllegalArgumentException();
        }
    }

    /**
     * Metodo per la scansione degli ordini di un passeggero.
     *
     * @param p Passeggero.
     * @param fare Tariffa di viaggio.
     * @param flight Numero del volo.
     * @throws RuntimeException Segnala un errore durante l'esecuzione del processo.
     */
    public void fetchOrder(Passenger p, Fares fare, Flight flight) throws RuntimeException {
        passengerService.persist(p);

        Booking booking = new Booking();
        booking.setPassengerId(p.getId());
        booking.setFlightId(flight.getId());
        booking.setFare(fare);
        booking.setCardDetails(Integer.parseInt(
                Factory.getInstance().getSession().getInfo().getCardNumber()));
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
     * Metodo per l'invio tramite e-mail del biglietto.
     *
     * @param booking Viene passata come argomento la prenotazione.
     * @throws IOException Segnala che si è verificato un errore durante le operazioni di I/O.
     * @throws RuntimeException Segnala un errore durante l'esecuzione del processo.
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
     * Metodo che tiene traccia dei posti disponibili a bordo di un volo.
     *
     * @param flight Numero del volo.
     */
    public void bookSeat(Flight flight) {
        flight.setSeats(flight.getSeats()-1);
        service.update(flight);
    }
}
