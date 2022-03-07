package model.persistence.dao;

import model.flight.aircraft.Manufacturer;
import model.persistence.Connection;
import model.persistence.entity.Aircraft;
import org.hibernate.query.Query;
import java.util.List;

public class AircraftDao implements AircraftDaoInterface{
    private Connection conn;

    public AircraftDao() {
        this.conn = new Connection();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Aircraft> findAll() {
        List<Aircraft> aircrafts = (List<Aircraft>) conn.getCurrentSession().createQuery("from Aircraft ").list();
        return  aircrafts;
    }

    @Override
    public List<Aircraft> findByMan(Manufacturer manufacturer) {
        String hql = "from Aircraft a where a.manufacturer like :man ";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("man","%" + manufacturer + "%");
        query.setCacheable(true);
        List<Aircraft> aircrafts = query.list();
        return aircrafts;
    }

    @Override
    public List<Aircraft> findByModel(String model) {
        String hql = "from Aircraft a where a.model like :model ";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("model","%" + model + "%");
        query.setCacheable(true);
        List<Aircraft> aircrafts = query.list();
        return aircrafts;
    }

    @Override
    public List<Aircraft> findByTailNumber(int tailNumber) {
        String hql = "from Aircraft a where a.tailNumber = :tail";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("tail",tailNumber);
        query.setCacheable(true);
        List<Aircraft> aircrafts = query.list();
        return   aircrafts;
    }

    @Override
    public List<Aircraft> findAvailable() {
        //String hql = "from Aircraft a where a.availability = true ";
        //Query query = conn.getCurrentSession().createQuery(hql);
        //query.setCacheable(true);
        List<Aircraft> aircrafts = null;
        return aircrafts;
    }

    @Override
    public void persist(Aircraft entity) {
        conn.getCurrentSession().save(entity);
    }

    @Override
    public void update(Aircraft entity) {
        conn.getCurrentSession().update(entity);
    }

    @Override
    public void delete(Aircraft entity) {
        conn.getCurrentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<Aircraft> entityList = findAll();
        for (Aircraft entity : entityList) {
            delete(entity);
        }
    }
}
