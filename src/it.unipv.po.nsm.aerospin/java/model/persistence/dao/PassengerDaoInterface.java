package model.persistence.dao;

import model.persistence.entity.Passenger;

import java.util.List;

public interface PassengerDaoInterface {
    List<Passenger> findAll();
    List<Passenger> findById(int id);
    List<Passenger> findByName(String name);
    List<Passenger> findBySurname(String surname);

}
