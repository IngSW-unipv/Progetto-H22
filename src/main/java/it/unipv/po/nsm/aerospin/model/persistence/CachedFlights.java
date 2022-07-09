package model.persistence;

import model.persistence.entity.Flight;
import model.persistence.service.FlightService;
import java.util.List;

/**
 * Classe Singleton che si occupa di memorizzare in locale i voli disponibili sul database
 *
 * @author GruppoNoSuchMethod
 */
public class CachedFlights {
    private static CachedFlights instance = null;
    private final FlightService flightService;
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

    // Metodo che resetta la lista cachedFindAll
    public void clearCache() {
        cachedFindAll = null;
    }
}
