package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Flight;
import model.persistence.entity.Passenger;
import org.hibernate.query.Query;

import java.util.List;

public class PassengerDao implements PassengerDaoInterface{

    private Connection conn;

    public PassengerDao() {
        this.conn = new Connection();
    }

    public Connection getConn(){
        return conn;
    }

    public void setConn(Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Passenger> findAll() {
        List<Passenger> passengers = (List<Passenger>) conn.getCurrentSession().createQuery("from Passenger ").list();
        return  passengers;
    }

    @Override
    public List<Passenger> findById(int id) {
        String hql = "from Passenger a where a.id = :id";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        //query.setCacheable(true);
        List<Passenger> passengers = query.list();
        return passengers;
    }

    @Override
    public List<Passenger> findByName(String name) {
        String hql = "from Passenger a where a.name = :name";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("name",name);
        //query.setCacheable(true);
        List<Passenger> passengers = query.list();
        return passengers;
    }

    @Override
    public List<Passenger> findBySurname(String surname) {
        String hql = "from Passenger a where a.surname = :surname";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("surname",surname);
        //query.setCacheable(true);
        List<Passenger> passengers = query.list();
        return passengers;
    }

    @Override
    public void persist(Passenger entity) {
        conn.getCurrentSession().save(entity);
    }

    @Override
    public void update(Passenger entity) {
        conn.getCurrentSession().update(entity);
    }

    @Override
    public void delete(Passenger entity) {
        conn.getCurrentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<Passenger> entityList = findAll();
        for (Passenger entity : entityList) {
            delete(entity);
        }
    }


}
