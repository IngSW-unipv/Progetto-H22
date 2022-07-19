package controller.util.manager;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.persistence.entity.Airport;
import model.persistence.entity.Route;
import model.persistence.service.RouteService;

import java.util.List;
import java.util.stream.Collectors;

public class RouteManager {
    RouteService service = new RouteService();
    private final List<Route> routes = service.findAll();

    public ObservableList<String> getDepartures() {
        List<String> departures = routes.stream()
                .map(o->o.getAirportDep().getAirportName())
                .sorted().distinct().collect(Collectors.toList());
        return FXCollections.observableArrayList(departures);
    }

    public ObservableList<String> getArrival(String departure) {

        List<String> arrival = routes.stream()
                .filter(o -> o.getAirportDep().equalsString(departure))
                .map(o->o.getAirportArr().getAirportName())
                .sorted().distinct().collect(Collectors.toList());
        return FXCollections.observableArrayList(arrival);
    }

    public Route getRouteByDepArr(String departure, String arrival){
        List<Route> route = routes.stream()
                .filter(o -> o.getAirportDep().equalsString(departure))
                .filter(o -> o.getAirportArr().equalsString(arrival))
                .distinct().toList();

        return route.get(0);
    }
}
