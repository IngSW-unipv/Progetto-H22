package model.persistence.dao;

import model.persistence.entity.Passenger;

import java.util.List;

public interface PassengerDaoInterface {
    List<Passenger> findAll();
    List<Passenger> findById(int id);
    List<Passenger> findByName(String name);
    List<Passenger> findBySurname(String surname);
    void persist(Passenger entity);
    void update(Passenger entity);
    void delete(Passenger entity);
    void deleteAll();

}
