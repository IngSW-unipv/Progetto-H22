package model.management;

import model.persistence.entity.Flight;
import model.persistence.service.FlightService;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class ResultManager {


    private List<Flight> flights = new ArrayList<>();
    private FlightService flightService = new FlightService();
    private Flight flight = new Flight();

    public List<Flight> getFlights(){
        flights = flightService.findAll();
        return flights;
    }

    public List<Flight> getFlightsByDepArr(String dep, String arr, String scheduledDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = Date.valueOf(scheduledDate);
        List<Flight> flights = flightService.findAll();
        return flights.stream().filter(f -> f.getRouteByFlightRouteId().getAirportByDeparture().getAirportName().equals(dep) &&
                f.getRouteByFlightRouteId().getAirportByArrival().getAirportName().equals(arr) &&
                f.getScheduledDate().compareTo(date) == 0).collect(Collectors.toList());

    }

}
