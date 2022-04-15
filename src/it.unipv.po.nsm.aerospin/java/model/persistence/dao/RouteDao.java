package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.daoInterface.RouteDaoInterface;
import model.persistence.entity.Airport;
import model.persistence.entity.Route;
import model.persistence.service.AirportService;
import org.hibernate.query.Query;

import java.util.List;

public class RouteDao implements RouteDaoInterface {

    private Connection conn;

    public RouteDao() {
        this.conn = new Connection();
    }

    @Override
    public List<Route> findAll() {
        String query = "from Route";
        Query q = conn.getCurrentSession().createQuery(query);
        List<Route> routes = (List<Route>) q.list();
        return  routes;
    }

    @Override
    public List<Route> findById(int id) {
        String hql = "from Route a where a.id = :id";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        query.setCacheable(true);
        List<Route> routes = query.list();
        return routes;
    }

    @Override
    public List<Route> findByDepIcao(String dep) {
        String hql = "from Route a where a.departure = :dep";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("dep",dep);
        //query.setCacheable(true);
        List<Route> routes = query.list();
        return routes;
    }

    @Override
    public List<Route> findByDepName(String dep) {
        AirportService airportService = new AirportService();
        Airport airport = new Airport();
        airport = airportService.findByName(dep).get(0);
        String depIcao = airport.getIcao();

        String hql = "from Route a where a.departure = :depIcao ";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("depIcao",depIcao);
        //query.setCacheable(true);
        List<Route> routes = query.list();
        return routes;
    }

    @Override
    public List<Route> findByArr(String arr) {
        String hql = "from Route a where a.arrival = :arr order by a.arrival_ICAO ASC";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("arr",arr);
        //query.setCacheable(true);
        List<Route> routes = query.list();
        return routes;
    }

    @Override
    public Route findByDepArr(String dep, String arr) {
        AirportService airportService = new AirportService();
        Airport departureAirport = airportService.findByName(dep).get(0);
        Airport arrivalAirport = airportService.findByName(arr).get(0);

        String hql = "from Route a where a.departure = :dep and a.arrival = :arr";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("dep",departureAirport.getIcao());
        query.setParameter("arr",arrivalAirport.getIcao());
        Route route = (Route) query.list().get(0);
        return route;
    }

    /*@Override
    public boolean checkRoute(String dep, String arr) {

        String hql = "from Route a where a.departure = :dep and a.arrival = :arr";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("dep",dep);
        query.setParameter("arr",arr);
        //query.setCacheable(true);

        try {
            Route route = (Route) query.list().get(0);
            return true;
        }catch (IndexOutOfBoundsException e){
            return false;
        }
    }*/

    @Override
    public void persist(Route entity) {
        conn.getCurrentSession().save(entity);
    }

    @Override
    public void update(Route entity) {
        conn.getCurrentSession().update(entity);
    }

    @Override
    public void delete(Route entity) {
        conn.getCurrentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<Route> entityList = findAll();
        for (Route entity : entityList) {
            delete(entity);
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn){
        this.conn = conn;
    }
}
