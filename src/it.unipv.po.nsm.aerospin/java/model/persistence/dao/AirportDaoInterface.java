package model.persistence.dao;


import model.persistence.entity.Airport;

import java.util.List;

public interface AirportDaoInterface {

     List<Airport> findAll();
     public void persist(Airport entity);
     public void update(Airport entity);
     public void delete(Airport entity);
     public void deleteAll();

}
