package model.persistence.service;

import model.persistence.dao.AirportDao;

import model.persistence.dao.AirportDao;
import model.persistence.entity.Aircraft;
import model.persistence.entity.Airport;

import java.util.List;

public class AirportService {
    private static AirportDao airportDao;

    public AirportService() {
        airportDao = new AirportDao();
    }


    public List<Airport> findByIcao(String id) {
        Thread thread = new Thread(()->{});
        airportDao.getConn().openCurrentSession();
        List<Airport> airports = airportDao.findByIcao(id);
        airportDao.getConn().closeCurrentSession();
        return airports;
    }
    public List<String> findByParam(String id) {
        airportDao.getConn().openCurrentSession();
        List<String> airports = airportDao.findByParam(id);
        airportDao.getConn().closeCurrentSession();
        return airports;
    }

    public List<Airport> findByIata(String iata) {
        airportDao.getConn().openCurrentSession();
        List<Airport> airports = airportDao.findByIata(iata);
        airportDao.getConn().closeCurrentSession();
        return airports;
    }

    public List<Airport> findByCity(String city) {
        airportDao.getConn().openCurrentSession();
        List<Airport> airports = airportDao.findByCity(city);
        airportDao.getConn().closeCurrentSession();
        return airports;
    }

    public List<Airport> findByName(String name) {
        airportDao.getConn().openCurrentSession();
        List<Airport> airports = airportDao.findByName(name);
        airportDao.getConn().closeCurrentSession();
        return airports;
    }

    public List<Airport> findAll() {
        airportDao.getConn().openCurrentSession();
        List<Airport> airports = airportDao.findAll();
        airportDao.getConn().closeCurrentSession();
        return airports;
    }

    public void persist(Airport aircraft) {
        airportDao.getConn().openCurrentSessionwithTransaction();
        airportDao.persist(aircraft);
        airportDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void update(Airport aircraft) {
        airportDao.getConn().openCurrentSessionwithTransaction();
        airportDao.update(aircraft);
        airportDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void delete(Airport aircraft) {
        airportDao.getConn().openCurrentSessionwithTransaction();
        airportDao.delete(aircraft);
        airportDao.getConn().closeCurrentSessionwithTransaction();
    }
    public void deleteAll() {
        airportDao.getConn().openCurrentSessionwithTransaction();
        airportDao.deleteAll();
        airportDao.getConn().closeCurrentSessionwithTransaction();
    }

    public AirportDao airportDao() {
        return airportDao;
    }


}
