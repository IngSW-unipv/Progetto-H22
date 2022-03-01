package model.flight.route;

import model.persistence.service.RouteService;
import model.persistence.entity.Route;

import java.util.ArrayList;
import java.util.List;

public class Search {


    public List<String> getServedDepartures(){
        List<String> airportsNames =  new ArrayList<>();
        RouteService routeService = new RouteService();
        List<Route> routes = routeService.findAll();
        for (int i = 0; i < routes.size(); i++) {
            airportsNames.add(routes.get(i).getDepartureName());
        }
        return airportsNames;
    }

    public List<String> getServedArrivals(){
        List<String> airportsNames =  new ArrayList<>();
        RouteService routeService = new RouteService();
        List<Route> routes = routeService.findAll();
        for (int i = 0; i < routes.size(); i++) {
            airportsNames.add(routes.get(i).getArrivalName());
        }
        return airportsNames;
    }

}
