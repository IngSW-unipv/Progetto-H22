package controller.util.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Factory;
import model.exception.NoMatchException;
import model.persistence.entity.Booking;
import model.persistence.service.BookingService;
import java.util.List;

/**
 * Classe Manager che si occupa di gestire la logica dell'applicativo, relativa all'Account.
 *
 * @author GruppoNoSuchMethod
 */
public class AccountManager {
    private final BookingService service = new BookingService();

    /**
     * Metodo per ordinare le prenotazioni di un cliente.
     *
     * @return -1 Se l'ordinamento tramite confronto è riuscito, 0 altrimenti.
     * @throws NoMatchException Segnala se il confronto non è andato a buon fine.
     */
    public ObservableList<Booking> getOrders() throws NoMatchException {
        List<Booking> booking = service.findByUser(Factory.getInstance().getSession().getUser());
        booking.sort((o1, o2) -> {
                if(o1.getOrderDate().before(o2.getOrderDate())) {
                    return -1;
                } else {
                    return 0;
                }
        });
        if(booking.size() == 0){
            throw new NoMatchException("Not Matched!\n");
        } else {
            return FXCollections.observableArrayList(booking);
        }
    }

    /**
     * Metodo per la creazione di una stringa relativa ai dati della prenotazione.
     *
     * @param booking Viene passata come argomento la prenotazione.
     * @return Viene generata una stringa che contiene tutti i dettagli della prenotazione.
     */
    public String getDetailText(Booking booking) {
        return "Il " + booking.getOrderDate()
                + " hai acquistato il volo " + booking.getFlightById().getFlightNumber()
                + "\nda " + booking.getFlightById().getRouteById().getAirportDep().getAirportName()
                + "\na " + booking.getFlightById().getRouteById().getAirportArr().getAirportName()
                + "\ndel " + booking.getFlightById().getScheduledDate()
                + "\nal prezzo di " + booking.getPrice()
                + " €\ncon la tariffa " + booking.getFare()
                + "\npagato con carta che termina *" + booking.getCardDetails()
                + "\nper il passeggero " + booking.getPassengerById().getSurname()
                + " " + booking.getPassengerById().getName();
    }
}
