package model.persistence.dao;


import model.persistence.entity.Airport;

import java.util.List;

public interface AirportDaoInterface {

     List<Airport> findByName(String name);
     List<String> findByParam(String param);
     List<Airport> findByIcao(String icao);
     List<Airport> findByIata(String iata);
     List<Airport> findByCity(String city);
     List<Airport> findAll();
     public void persist(Airport entity);
     public void update(Airport entity);
     public void delete(Airport entity);
     public void deleteAll();

}
