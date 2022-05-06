package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.dao.AirportDaoInterface;
import model.persistence.entity.Airport;
import org.hibernate.query.Query;


import java.util.List;

public class AirportDao implements AirportDaoInterface {
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
}
