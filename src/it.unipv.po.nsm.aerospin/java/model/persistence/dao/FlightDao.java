package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Flight;
import java.util.List;

public class FlightDao implements IDao<Flight> {
    private final Connection conn;

    public FlightDao() {
        this.conn = new Connection();
    }

    public Connection getConn() {
        return conn;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Flight> findAll() {
        return(List<Flight>) conn.getCurrentSession().createQuery("from Flight ").list();
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
