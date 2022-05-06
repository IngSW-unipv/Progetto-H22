package model.persistence.service;


import model.persistence.dao.FlightDao;
import model.persistence.entity.Flight;

import java.util.List;

public class FlightService {

    private static FlightDao flightDao;

    public FlightService() {
        flightDao = new FlightDao();
    }

    public List<Flight> findAll() {
        flightDao.getConn().openCurrentSession();
        List<Flight> flights = flightDao.findAll();
        flightDao.getConn().closeCurrentSession();
        return flights;
    }


    public void persist(Flight flight) {
        flightDao.getConn().openCurrentSessionwithTransaction();
        flightDao.persist(flight);
        flightDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void update(Flight flight) {
        flightDao.getConn().openCurrentSessionwithTransaction();
        flightDao.update(flight);
        flightDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void delete(Flight flight) {
        flightDao.getConn().openCurrentSessionwithTransaction();
        flightDao.delete(flight);
        flightDao.getConn().closeCurrentSessionwithTransaction();
    }
    public void deleteAll() {
        flightDao.getConn().openCurrentSessionwithTransaction();
        flightDao.deleteAll();
        flightDao.getConn().closeCurrentSessionwithTransaction();
    }
}
