package model.persistence.dao;

import model.persistence.entity.Flight;

import java.util.Date;
import java.util.List;

public interface FlightDaoInterface {
    List<Flight> findAll();
    List<Flight> findById(int id);
    List<Flight> findByDate(Date date);
    public void persist(Flight entity);
    public void update(Flight entity);
    public void delete(Flight entity);
    public void deleteAll();
}
