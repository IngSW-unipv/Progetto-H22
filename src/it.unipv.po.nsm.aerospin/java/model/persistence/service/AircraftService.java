package model.persistence.service;

import model.flight.aircraft.Manufacturer;
import model.persistence.dao.AircraftDao;
import model.persistence.entity.Aircraft;
import model.persistence.entity.Airport;

import java.util.List;

public class AircraftService {
    private static AircraftDao aircraftDao;

    public AircraftService() {
        aircraftDao = new AircraftDao();
    }

    public List<Aircraft> findAll() {
        aircraftDao.getConn().openCurrentSession();
        List<Aircraft> aircrafts = aircraftDao.findAll();
        aircraftDao.getConn().closeCurrentSession();
        return aircrafts;
    }

    public List<Aircraft> findByMan(Manufacturer man) {
        aircraftDao.getConn().openCurrentSession();
        List<Aircraft> aircrafts = aircraftDao.findByMan(man);
        aircraftDao.getConn().closeCurrentSession();
        return aircrafts;
    }

    public List<Aircraft> findByModel(String model) {
        aircraftDao.getConn().openCurrentSession();
        List<Aircraft> aircrafts = aircraftDao.findByModel(model);
        aircraftDao.getConn().closeCurrentSession();
        return aircrafts;
    }

    public List<Aircraft> findByTailNumber(int tailNumber) {
        aircraftDao.getConn().openCurrentSession();
        List<Aircraft> aircrafts = aircraftDao.findByTailNumber(tailNumber);
        aircraftDao.getConn().closeCurrentSession();
        return aircrafts;
    }

    public List<Aircraft> findAvailable() {
        aircraftDao.getConn().openCurrentSession();
        List<Aircraft> aircrafts = aircraftDao.findAvailable();
        aircraftDao.getConn().closeCurrentSession();
        return aircrafts;
    }




}
