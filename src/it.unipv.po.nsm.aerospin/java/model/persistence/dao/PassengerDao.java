package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Crew;
import model.persistence.entity.Passenger;

import java.util.List;

public class PassengerDao implements PassengerDaoInterface{

    private Connection conn;

    public PassengerDao() {
        this.conn = new Connection();
    }

    @Override
    public List<Passenger> findAll() {
        List<Passenger> passengers = (List<Passenger>) conn.getCurrentSession().createQuery("from Passenger ").list();
        return  passengers;
    }

    @Override
    public List<Passenger> findById(int id) {
        return null;
    }

    @Override
    public List<Passenger> findByName(String name) {
        return null;
    }

    @Override
    public List<Passenger> findBySurname(String surname) {
        return null;
    }

    @Override
    public List<Passenger> findByFlightNumber(String flightNumber) {
        return null;
    }

    @Override
    public List<Passenger> findByClassType(String classType) {
        return null;
    }
}
