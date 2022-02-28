package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Flight;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class FlightDao implements FlightDaoInterface{

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
    public List<Flight> findById(int id) {
        String hql = "from Flight a where a.id = :id";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        //query.setCacheable(true);
        List<Flight> flights = query.list();
        return flights;
    }

    @Override
    public List<Flight> findByDate(Date date) {
        String hql = "from Flight a where a.scheduledDate = :date";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("date",date);
        //query.setCacheable(true);
        List<Flight> flights = query.list();
        return flights;
    }
}
