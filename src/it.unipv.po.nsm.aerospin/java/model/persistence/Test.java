package model.persistence;


import model.persistence.entity.Airport;
import model.persistence.service.AirportService;


import java.util.List;

public class Test {
    public static void main(String[] args) {


        AirportService airportService = new AirportService();
        List<Airport> airports = airportService.findAll();
        for (Airport a : airports) {
            System.out.println(a);
        }
        System.exit(0);


    }
}
