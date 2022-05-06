package model.persistence.dao;


import model.persistence.entity.Airport;
import java.util.List;

public interface AirportDaoInterface {

     List<Airport> findAll();
     void persist(Airport entity);
     void update(Airport entity);
     void delete(Airport entity);
     void deleteAll();

}
