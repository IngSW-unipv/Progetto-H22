package model.management;

import model.management.FlightManager;

import java.util.List;

public class TestFlightManager {
    public static void main(String[] args) {
        FlightManager flightManager = new FlightManager();
        List<String> fleet = flightManager.getServedRoutes();
        for (String s: fleet) {
            System.out.println(s);
        }

        System.exit(0);
    }
}
