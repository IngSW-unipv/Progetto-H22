package model.persistence.service;

import model.persistence.dao.AirportDao;
import model.persistence.dao.BookDao;
import model.persistence.entity.Airport;
import model.persistence.entity.Book;

import java.util.List;

public class AirportService {
    private static AirportDao airportDao;

    public AirportService() {
        airportDao = new AirportDao();
    }




    public List<Airport> findByIcao(String id) {
        airportDao.getConn().openCurrentSession();
        List<Airport> airports = airportDao.findByIcao(id);
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


    public AirportDao airportDao() {
        return airportDao;
    }
}
