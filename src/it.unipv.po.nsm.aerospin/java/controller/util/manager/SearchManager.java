package controller.util.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import model.persistence.CachedFlights;
import model.persistence.entity.Flight;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe Manager che si occupa di gestire la logica dell'applicativo, relativa alla Search.
 *
 * @author GruppoNoSuchMethod
 */
public class SearchManager {
    private final List<Flight> results = CachedFlights.getInstance().findAll();

    public ObservableList<String> getDepartures() {
        List<String> departures = results.stream()
                .map(o->o.getRouteById().getAirportDep().getAirportName())
                .sorted().distinct().collect(Collectors.toList());
        return FXCollections.observableArrayList(departures);
    }

    public ObservableList<String> getArrivals(String departure) {
        List<String> arrivals = results.stream()
                .filter(o -> o.getRouteById().getAirportDep().equalsString(departure))
                .map(o->o.getRouteById().getAirportArr().getAirportName())
                .sorted().distinct().collect(Collectors.toList());
        return FXCollections.observableArrayList(arrivals);
    }

    /*  Controllo se posso selezionare a/r
     *  in base agli aereoporti selezionati
     */
    public boolean checkRoute(String dep, String ret) {
        List<Flight> a = results.stream()
                .filter(o -> o.getRouteById().getAirportDep().equalsString(dep))
                .filter(o -> o.getRouteById().getAirportArr().equalsString(ret))
                .distinct().toList();
        return a.size() > 0;
    }

    /*  Mantengo le date selezionabili tra
     *  Oggi e i prossimi 4 Mesi
     */
    public Callback<DatePicker, DateCell> bookingRange(LocalDate from) {
        int maxMonth = 4;
        return new Callback<>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        LocalDate after = from.plusMonths(maxMonth);
                        setDisable(empty || date.isBefore(from) || date.isAfter(after));
                    }
                };
            }
        };
    }
}
