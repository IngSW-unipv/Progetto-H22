package controller;

import model.manager.ResultManager;
import model.persistence.entity.Flight;
import model.persistence.service.FlightService;

import java.util.Date;
import java.util.List;

public class TestResult {
    public static void main(String[] args) {


        ResultManager resultManager = new ResultManager();
        FlightService flightService = new FlightService();
        List<Flight> flights = flightService.findAll();

        Date date = flightService.getFlightDate("AES480");

        System.out.println();







    }
}


