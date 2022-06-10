package model.persistence;

import model.persistence.entity.Flight;
import model.persistence.service.FlightService;
import java.util.List;

public class CachedFlights {
    private static CachedFlights instance = null;
    private static FlightService flightService;

    // Lista che conterrà tutti i risultati che verranno trovati da database
    private List<Flight> cachedFindAll = null;

    private CachedFlights() {
        flightService = new FlightService();
    }

    public static CachedFlights getInstance() {
        if (instance == null) {
            instance = new CachedFlights();
        }
        return instance;
    }

    public List<Flight> findAll() {
        if (cachedFindAll == null) {
            cachedFindAll = flightService.findAll();
        }
        return cachedFindAll;
    }


    // Metodo che pone a null la lista cachedFindAll
    public void clearCache() {
        cachedFindAll = null;
    }
}
