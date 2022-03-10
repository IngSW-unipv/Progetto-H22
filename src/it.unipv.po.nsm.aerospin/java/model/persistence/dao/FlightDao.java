package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Employee;
import model.persistence.entity.Flight;
import model.persistence.entity.Route;
import model.persistence.service.RouteService;
import org.hibernate.query.Query;

import java.sql.Date;
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

    @Override
    public List<Flight> findFlightsByDate(String dep, String arr, String scheduledDate) {

        Date date =Date.valueOf(scheduledDate);
        RouteService routeService = new RouteService();
        Route route = routeService.findBydepArr(dep,arr);
        String hql = "from Flight a where a.scheduledDate = :date and a.routeId = :routeId";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("date",date);
        query.setParameter("routeId",route.getRouteId());
        List<Flight> flights = query.list();
        return flights;
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
