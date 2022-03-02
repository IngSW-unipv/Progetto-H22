package model.flight;

import model.persistence.entity.Aircraft;
import model.persistence.entity.Route;
import model.persistence.service.AircraftService;
import model.persistence.service.RouteService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlightManager {

    private List<String> fleet = new ArrayList<>();
    private List<String> servedRoutes = new ArrayList<>();
    private AircraftService aircraftService = new AircraftService();
    private RouteService routeService = new RouteService();

    public List<String> getFleet(){
        fleet.clear();
        List<Aircraft> aircrafts = aircraftService.findAll();
        for (int i = 0; i < aircrafts.size(); i++) {
            fleet.add(aircrafts.get(i).getTailNumber());
        }
        fleet.sort(Comparator.naturalOrder());
        return fleet.stream().distinct().collect(Collectors.toList());
    }

    public List<String> getServedRoutes(){
        servedRoutes.clear();
        List<Route> routes = routeService.findAll();
        for (int i = 0; i < routes.size(); i++) {
            servedRoutes.add(routes.get(i).getRouteId());
        }
        servedRoutes.sort(Comparator.naturalOrder());
        return servedRoutes.stream().distinct().collect(Collectors.toList());
    }




}
