package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Airport;
import org.hibernate.query.Query;


import java.util.List;

public class AirportDao implements AirportDaoInterface{
    private Connection conn;

    public AirportDao() {
        this.conn = Connection.getInstance();
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
    public void persist(Airport entity) {
        conn.getCurrentSession().save(entity);
    }

    @Override
    public void update(Airport entity) {
        conn.getCurrentSession().update(entity);
    }

    @Override
    public void delete(Airport entity) {
        conn.getCurrentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<Airport> entityList = findAll();
        for (Airport entity : entityList) {
            delete(entity);
        }

    }


    @Override
    public List<String> findByParam(String param) {
        String hql = "select " + param + " from Airport" ;
        Query query = conn.getCurrentSession().createQuery(hql);
        //query.setParameter(1,param );
        //query.setCacheable(true);
        List<String> airports = query.list();
        return   airports;
    }


    @Override
    public List<Airport> findByName(String name) {
        Query query = conn.getCurrentSession().createNativeQuery("select * from Airport where AIRPORT_NAME = :name order by AIRPORT_NAME asc" ).addEntity(Airport.class);
        query.setParameter("name","%" + name + "%");
        //query.setCacheable(true);
        List<Airport> airports = query.list();
        return airports;
    }

    public List<Airport> findByIcao(String icao) {
        Query query = conn.getCurrentSession().createNativeQuery("select * from Airport where ICAO = :icao order by AIRPORT_NAME asc" ).addEntity(Airport.class);
        query.setParameter("icao",icao);
        //query.setParameter("icao",icao);
        List<Airport> airport = (List<Airport>) query.list();
        return airport;
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
