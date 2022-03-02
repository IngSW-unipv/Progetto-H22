package model.management;

import model.persistence.entity.Aircraft;
import model.persistence.service.AircraftService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AircraftManager {

    private List<String> aircrafts = new ArrayList<>();
    private AircraftService aircraftService = new AircraftService();
    private List<Aircraft> aircraftsList = new ArrayList<>();

   public void saveAircraft(String manufacturer, String model, int seats, double maxRange ){
        Aircraft aircraft = new Aircraft();
        int i = 254;
        aircraft.setTailNumber("PV"+i);
        aircraft.setModel(model);
        aircraft.setManufacturer(manufacturer);
        aircraft.setSeats(seats);
        aircraft.setMaxRange(maxRange);
        aircraftService.persist(aircraft);
        i = i + 28;
   }




}
