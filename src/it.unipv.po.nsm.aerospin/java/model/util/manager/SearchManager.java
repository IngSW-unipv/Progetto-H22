package model.util.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;
import model.persistence.CachedFlights;
import model.persistence.entity.Flight;
import model.persistence.entity.Route;
import model.persistence.service.RouteService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchManager {

    CachedFlights cachedFlights = CachedFlights.getInstance();
    List<Flight> results = cachedFlights.findAll();

//    /*  Quando premo una lettera mentre uso un ComboBox
//     *  questo metodo seleziona e sposta il cursore sul primo item
//     *  che inizia con la input letter
//     */
//    public void selectOptionOnKey(ComboBox<String> cb, List<String> strings) {
//        cb.setOnKeyPressed(e -> {
//            KeyCode keyCode = e.getCode();
//
//            if (keyCode.isLetterKey()) {
//                char key = keyCode.getName().charAt(0);
//                SingleSelectionModel<String> cbSelectionModel = cb.getSelectionModel();
//                cbSelectionModel.select(0);
//
//                for (int i = 0; i < strings.size(); i++) {
//                    if(cbSelectionModel.getSelectedItem().charAt(0) == key) {
//                        cbSelectionModel.select(i);
//                        ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) cb.getSkin();
//                        ListView<?> list = (ListView<?>) skin.getPopupContent();
//                        list.scrollTo(i);
//                        return;
//                    }
//                    else {
//                        cbSelectionModel.selectNext();
//                    }
//                }
//            }
//        });
//    }

    /*  Mantengo le date selezionabili tra
     *  Oggi e i prossimi 4 Mesi
     */
    private static final int maxMonth = 4;
    public Callback<DatePicker, DateCell> bookingRange(LocalDate from) {
        return new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        LocalDate today = from;
                        LocalDate next = from.plusMonths(maxMonth);
                        setDisable(empty || date.isBefore(today) || date.isAfter(next));
                    }
                };
            }
        };
    }

    /*  Controllo se posso selezionare a/r
     *  in base agli aereoporti selezionati
     */
    public boolean checkRoute(String dep, String ret) {
        List<Flight> a = results.stream()
                .filter(o -> o.getRouteByFlightRouteId().getAirportByDeparture().equalsString(dep))
                .collect(Collectors.toList());

        List<Flight> b = a.stream()
                .filter(o -> o.getRouteByFlightRouteId().getAirportByArrival().equalsString(ret))
                .collect(Collectors.toList());

        if (b.size()>0){
            return true;
        } else {
            return false;
        }
    }


    public ObservableList<String> getDepartures() {
        List<String> departures;

        departures = results.stream()
                .map(o->o.getRouteByFlightRouteId().getAirportByDeparture().getAirportName()).sorted()
                .distinct().collect(Collectors.toList());

        return FXCollections.observableArrayList(departures);
    }

    public ObservableList<String> getArrivals(String departure) {
        List<String> arrivals;
        arrivals = results.stream()
                .filter(o -> o.getRouteByFlightRouteId().getAirportByDeparture().equalsString(departure))
                .map(o->o.getRouteByFlightRouteId().getAirportByArrival().getAirportName()).sorted()
                .distinct().collect(Collectors.toList());

        return FXCollections.observableArrayList(arrivals);
    }
}