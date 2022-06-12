package controller.util.manager;

import com.google.zxing.WriterException;
import com.sun.prism.impl.BaseMesh;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import model.Factory;
import model.booking.Ticket;
import model.booking.TicketMail;
import model.exception.NoMatchException;
import model.persistence.CachedFlights;
import model.persistence.entity.Flight;
import model.persistence.entity.Orders;
import model.persistence.service.FlightService;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ResultManager {
    private final FlightService service = new FlightService();
    private final List<Flight> results = CachedFlights.getInstance().findAll();

    private static final Pattern VALID_NAME_REGEX =
            Pattern.compile("^[a-zA-z]{2,15}$");

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
    public boolean isMinor(LocalDate birthDate){
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        return age.getYears() < 16;
    }
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

    public boolean dataCheck(String name, String surname) {
        Matcher matcher1 = VALID_NAME_REGEX.matcher(name);
        Matcher matcher2 = VALID_NAME_REGEX.matcher(surname);

        return matcher1.find() && matcher2.find();
    }

    public void bookSeat(Flight flight) {
        flight.setSeats(flight.getSeats()-1);
        service.update(flight);
    }

    public void sendTicket(Orders orders) throws IOException, WriterException {
        TicketMail service = new TicketMail();
        Ticket ticket;
        service.setText("Grazie per aver scelto il Aerospin.!");
        service.setSubject("Il Tuo Biglietto");

        ticket = new Ticket(orders.getPassengerById().getName(), orders.getPassengerById().getSurname(),
                            orders.getFlightById().getRouteById().getAirportDep().getIata(),
                            orders.getFlightById().getRouteById().getAirportArr().getIata(),
                            orders.getFlightById().getFlightNumber(), orders.getFlightById().getScheduledDate().toString(),
                            orders.getFlightById().getScheduledTime().toString(),
                            orders.getFlightById().getArrivalTime().toString());
        ticket.generateTicket();
        service.send(orders.getPassengerById().getUserById().getEmail(), ticket.getPath());
    }
}
