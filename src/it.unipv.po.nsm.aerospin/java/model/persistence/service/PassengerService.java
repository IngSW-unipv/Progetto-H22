package model.persistence.service;


import model.persistence.dao.PassengerDao;
import model.persistence.entity.Passenger;

import java.util.List;

public class PassengerService {
    private static PassengerDao passengerDao;

    public PassengerService() {
        passengerDao = new PassengerDao();
    }

    public List<Passenger> findAll() {
        passengerDao.getConn().openCurrentSession();
        List<Passenger> passengers = passengerDao.findAll();
        passengerDao.getConn().closeCurrentSession();
        return passengers;
    }

    public List<Passenger> findById(int id) {
        passengerDao.getConn().openCurrentSession();
        List<Passenger> passengers = passengerDao.findById(id);
        passengerDao.getConn().closeCurrentSession();
        return passengers;
    }

    public List<Passenger> findByName(String name) {
        passengerDao.getConn().openCurrentSession();
        List<Passenger> passengers = passengerDao.findByName(name);
        passengerDao.getConn().closeCurrentSession();
        return passengers;
    }

    public List<Passenger> findBySurname(String surname) {
        passengerDao.getConn().openCurrentSession();
        List<Passenger> passengers = passengerDao.findBySurname(surname);
        passengerDao.getConn().closeCurrentSession();
        return passengers;
    }
}
