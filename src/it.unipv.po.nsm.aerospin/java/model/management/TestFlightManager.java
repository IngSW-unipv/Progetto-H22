package model.management;

import model.management.FlightManager;
import model.persistence.entity.Flight;
import model.persistence.service.FlightService;

import java.util.List;

public class TestFlightManager {
    public static void main(String[] args) {
        FlightManager flightManager = new FlightManager();
        flightManager.saveFlight("ER250","quarta");

        System.exit(0);
    }
}
