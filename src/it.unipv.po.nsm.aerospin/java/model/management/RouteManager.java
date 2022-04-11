package model.management;

import model.persistence.entity.Airport;
import model.persistence.entity.Route;
import model.persistence.service.AirportService;
import model.persistence.service.RouteService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RouteManager {

    private List<String> airports;
    private List<Airport> airportList;
    private AirportService airportService = new AirportService();
    private RouteService routeService = new RouteService();

    public List<String> getAirports(){
        airports.clear();
        airportList = airportService.findAll();
        for (int i = 0; i < airportList.size(); i++) {
            airports.add(airportList.get(i).getAirportName());
        }
        airports.sort(Comparator.naturalOrder());

        return airports.stream().distinct().collect(Collectors.toList());
    }

    public void saveRoute(Airport departure, String arrival, String waypoints, double price){
        Route route = new Route();
        //route.setDeparture(departure);
        route.setWaypoints(waypoints);
        //route.setCostIndex(xxxxxxxxxxx);
        routeService.persist(route);

    }


}
