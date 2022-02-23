package model.persistence.dao;

import model.persistence.entity.Airport;


import java.util.List;

public interface AirportDaoInterface {

    public List<Airport> findByName(String name);
    public List<Airport> findByIcao(String icao);
    public List<Airport> findByIata(String iata);
    public List<Airport> findByCity(String city);
    public List<Airport> findAll();

}
