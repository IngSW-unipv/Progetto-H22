package model.management;

import model.persistence.entity.Flight;
import model.persistence.service.FlightService;

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
}
