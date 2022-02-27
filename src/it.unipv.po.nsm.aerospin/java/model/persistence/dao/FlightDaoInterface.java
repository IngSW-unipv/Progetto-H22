package model.persistence.dao;

import model.flight.aircraft.Manufacturer;

import model.persistence.entity.Flight;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface FlightDaoInterface {
    List<Flight> findAll();
    List<Flight> findById(int id);
    List<Flight> findByDate(Date date);
}
