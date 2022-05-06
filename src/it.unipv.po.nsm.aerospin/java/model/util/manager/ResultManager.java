package model.util.manager;

import model.persistence.entity.Flight;
import model.persistence.service.FlightService;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultManager {


    private List<Flight> flights = new ArrayList<>();
    private FlightService flightService = new FlightService();
    private Flight flight = new Flight();
    private String date;

    public List<Flight> getFlights(){
        flights = flightService.findAll();
        return flights;
    }

    public List<Flight> getFlightsByDepArr(String dep, String arr, String scheduledDate) {
        List<Flight> flights = flightService.findAll();
        List<Flight> results = new ArrayList<>();
        for (Flight f : flights) {
            date = f.getScheduledDate().toString().substring(0,10);
            if (f.getRouteByFlightRouteId().getAirportByDeparture().getAirportName().equals(dep)
                    && f.getRouteByFlightRouteId().getAirportByArrival().getAirportName().equals(arr) &&
                    date.equals(scheduledDate)) {
                results.add(f);
            }

        }

        return results;
    }

}
