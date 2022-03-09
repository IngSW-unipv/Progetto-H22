package model.management;

import model.persistence.entity.Airport;
import model.persistence.service.AirportService;
import model.persistence.service.RouteService;
import model.persistence.entity.Route;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchManager {

    private List<String> airportsNames =  new ArrayList<>();
    private RouteService routeService = new RouteService();
    private AirportService airportService = new AirportService();
    private Airport airport = new Airport();



    public List<String> getServedDepartures(){
        airportsNames.clear();
        List<Route> routes = routeService.findAll();
        for (int i = 0; i < routes.size(); i++) {
            airportsNames.add(routes.get(i).getDeparture().getAirportName());
        }
        airportsNames.sort(Comparator.naturalOrder());
        return airportsNames.stream().distinct().collect(Collectors.toList());

    }

    public List<String> getServedArrivals(String servedByDeparture){
        airportsNames.clear();
        List<Route> routes = routeService.findByDepName(servedByDeparture);
        airport = airportService.findByName(servedByDeparture).get(0);
        for (int i = 0; i < routes.size(); i++) {
            airportsNames.add(routes.get(i).getArrival().getAirportName());
        }
        airportsNames.sort(Comparator.naturalOrder());
        return airportsNames.stream().distinct().collect(Collectors.toList());

    }

    public boolean checkRoute(String depName, String arrName){
        Airport dep = airportService.findByName(depName).get(0);
        Airport arr = airportService.findByName(arrName).get(0);
        return routeService.checkRoute(dep.getIcao(),arr.getIcao());

    }










    //fai da arrival a departure per verificare se ci sono voli di ritorno

}
