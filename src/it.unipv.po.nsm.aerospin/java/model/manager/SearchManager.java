package model.manager;

import model.persistence.entity.Route;
import model.persistence.service.RouteService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchManager {

    private List<String> airportsNames =  new ArrayList<>();
    private RouteService routeService = new RouteService();
    List<Route> routes = routeService.findAll();


    public List<String> getServedDepartures(){
        airportsNames.clear();
        for (int i = 0; i < routes.size(); i++) {
            airportsNames.add(routes.get(i).getAirportByDeparture().getAirportName());
        }
        airportsNames.sort(Comparator.naturalOrder());
        return airportsNames.stream().distinct().collect(Collectors.toList());

    }

    public List<String> getServedArrivals(String servedByDeparture) {
        airportsNames.clear();
        for (int i = 0; i < routes.size(); i++) {
            if(routes.get(i).getAirportByDeparture().getAirportName().equals(servedByDeparture)){
                airportsNames.add(routes.get(i).getAirportByArrival().getAirportName());
            }
        }
            airportsNames.sort(Comparator.naturalOrder());
            return airportsNames.stream().distinct().collect(Collectors.toList());

    }

    public boolean checkRoute(String departure, String arrival){

        if(routes.stream().anyMatch(r -> r.getAirportByDeparture().getAirportName().equals(departure) && r.getAirportByArrival().getAirportName().equals(arrival))){
            return true;
        }
        return false;
    }


}