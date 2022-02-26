package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Airport;
import org.hibernate.query.Query;


import java.util.List;

public class AirportDao implements AirportDaoInterface{
    private Connection conn;

    public AirportDao() {
        this.conn = new Connection();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn){
        this.conn = conn;
    }

    @SuppressWarnings("unchecked")
    public List<Airport> findAll() {
        List<Airport> airports = (List<Airport>) conn.getCurrentSession().createQuery("from Airport ").list();
        return airports;
    }



    @Override
    public List<Airport> findByName(String name) {
        String hql = "from Airport a where a.airportName like :name ";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("name","%" + name + "%");
        //query.setCacheable(true);
        List<Airport> airports = query.list();
        return   airports;
    }

    public List<Airport> findByIcao(String icao) {
        String hql = "from Airport a where a.icao = :icao";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("icao",icao);
        //query.setCacheable(true);
        List<Airport> airports = query.list();
        return   airports;
    }

    @Override
    public List<Airport> findByIata(String iata) {
        String hql = "from Airport a where a.iata = :iata";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("iata",iata);
        //query.setCacheable(true);
        List<Airport> airports = query.list();
        return   airports;
    }

    @Override
    public List<Airport> findByCity(String city) {
        String hql = "from Airport a where a.city like :city ";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("city",city + "%");
        //query.setCacheable(true);
        List<Airport> airports = query.list();
        return   airports;
    }




}
