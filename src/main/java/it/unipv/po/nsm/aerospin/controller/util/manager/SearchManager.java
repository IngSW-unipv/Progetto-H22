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
 * Classe che si occupa di gestire alcune logiche del SearchController, abbassando l'accoppiamento
 *
 * @author GruppoNoSuchMethod
 */
public class SearchManager {
    private final List<Flight> results = CachedFlights.getInstance().findAll();

    /**
     * Metodo che restituisce gli aeroporti da cui parte almeno una qualsiasi rotta
     *
     * @return Lista di aeroporti di partenza
     */
    public ObservableList<String> getDepartures() {
        List<String> departures = results.stream()
                .map(o->o.getRouteById().getAirportDep().getAirportName())
                .sorted().distinct().collect(Collectors.toList());
        return FXCollections.observableArrayList(departures);
    }

    /**
     * Metodo che restituisce gli aeroporti raggiungibili da un dato aeroporto di partenza
     *
     * @param departure Aeroporto di partenza
     * @return Lista di aeroporti di arrivo
     */
    public ObservableList<String> getArrivals(String departure) {
        List<String> arrivals = results.stream()
                .filter(o -> o.getRouteById().getAirportDep().equalsString(departure))
                .map(o->o.getRouteById().getAirportArr().getAirportName())
                .sorted().distinct().collect(Collectors.toList());
        return FXCollections.observableArrayList(arrivals);
    }

    /**
     * Metodo che verifica l'esistenza di un volo di ritorno tra gli aereoporti selezionati
     *
     * @param dep Aeroporto di partenza
     * @param ret Aeroporto di arrivo
     * @return true se andata/ritorno disponibile, false altrimenti.
     */
    public boolean checkRoute(String dep, String ret) {
        List<Flight> a = results.stream()
                .filter(o -> o.getRouteById().getAirportDep().equalsString(dep))
                .filter(o -> o.getRouteById().getAirportArr().equalsString(ret))
                .distinct().toList();
        return a.size() > 0;
    }

    /**
     * Metodo che rende selezionabili le date da una data e per i futuri 4 mesi
     *
     * @param from Data di partenza
     * @return Callback Object per il DatePicker
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
