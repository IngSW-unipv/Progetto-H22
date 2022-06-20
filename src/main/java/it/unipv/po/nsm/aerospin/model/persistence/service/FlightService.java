package model.persistence.service;

import model.persistence.dao.FlightDao;
import model.persistence.entity.Flight;
import java.util.List;

/**
 * Classe Service che si occupa di gestire la logica delle azioni richieste dal cliente nell'applicativo.
 *
 * @author GruppoNoSuchMethod
 */
public class FlightService implements IService<Flight> {
    private static FlightDao flightDao;

    public FlightService() {
        flightDao = new FlightDao();
    }

    @Override
    public List<Flight> findAll() {
        flightDao.getConn().openCurrentSession();
        List<Flight> flights = flightDao.findAll();
        flightDao.getConn().closeCurrentSession();
        return flights;
    }

    @Override
    public void persist(Flight flight) {
        flightDao.getConn().openCurrentSessionWithTransaction();
        flightDao.persist(flight);
        flightDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Flight flight) {
        flightDao.getConn().openCurrentSessionWithTransaction();
        flightDao.update(flight);
        flightDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(Flight flight) {
        flightDao.getConn().openCurrentSessionWithTransaction();
        flightDao.delete(flight);
        flightDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteAll() {
        flightDao.getConn().openCurrentSessionWithTransaction();
        flightDao.deleteAll();
        flightDao.getConn().closeCurrentSessionWithTransaction();
    }
}
