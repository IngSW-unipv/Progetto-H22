package model.persistence.service;


import model.persistence.dao.FlightDao;
import model.persistence.entity.Flight;

import java.util.Date;
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

    public List<Flight> findById(int id) {
        flightDao.getConn().openCurrentSession();
        List<Flight> flights = flightDao.findById(id);
        flightDao.getConn().closeCurrentSession();
        return flights;
    }

    public List<Flight> findByDate(Date date) {
        flightDao.getConn().openCurrentSession();
        List<Flight> flights = flightDao.findByDate(date);
        flightDao.getConn().closeCurrentSession();
        return flights;
    }
}
