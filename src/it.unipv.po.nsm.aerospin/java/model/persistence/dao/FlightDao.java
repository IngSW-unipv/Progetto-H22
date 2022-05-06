package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Flight;

import java.util.List;

public class FlightDao implements FlightDaoInterface {

    private Connection conn;

    public FlightDao() {
        this.conn = new Connection();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn){
        this.conn = conn;
    }


    @Override
    public List<Flight> findAll() {
        List<Flight> flights = (List<Flight>) conn.getCurrentSession().createQuery("from Flight ").list();
        return  flights;
    }


    @Override
    public void persist(Flight entity) {
        conn.getCurrentSession().save(entity);
    }

    @Override
    public void update(Flight entity) {
        conn.getCurrentSession().update(entity);
    }

    @Override
    public void delete(Flight entity) {
        conn.getCurrentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<Flight> entityList = findAll();
        for (Flight entity : entityList) {
            delete(entity);
        }
    }
}
