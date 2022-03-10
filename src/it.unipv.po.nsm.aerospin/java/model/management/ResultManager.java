package model.management;

import model.persistence.entity.Flight;
import model.persistence.service.FlightService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ResultManager {


    private List<Flight> flights = new ArrayList<>();
    private FlightService flightService = new FlightService();
    private Flight flight = new Flight();

    public List<Flight> getFlights(){
        flights = flightService.findAll();
        return flights;
    }

    public List<Flight> getFlightsByDepArr(String dep, String arr, String scheduledDate){
        List<Flight> flights = flightService.findFlightsByDate(dep,arr,scheduledDate);
        return flights;
    }

}
