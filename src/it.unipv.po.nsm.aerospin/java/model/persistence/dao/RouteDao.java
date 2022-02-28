package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Passenger;
import model.persistence.entity.Route;
import org.hibernate.query.Query;

import java.util.List;

public class RouteDao implements RouteDaoInterface{

    private Connection conn;

    public RouteDao() {
        this.conn = new Connection();
    }

    @Override
    public List<Route> findAll() {
        List<Route> routes = (List<Route>) conn.getCurrentSession().createQuery("from Route ").list();
        return  routes;
    }

    @Override
    public List<Route> findById(int id) {
        String hql = "from Route a where a.id = :id";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        //query.setCacheable(true);
        List<Route> routes = query.list();
        return routes;
    }

    @Override
    public List<Route> findByDep(String dep) {
        String hql = "from Route a where a.departure = :dep";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("dep",dep);
        //query.setCacheable(true);
        List<Route> routes = query.list();
        return routes;
    }

    @Override
    public List<Route> findByArr(String arr) {
        String hql = "from Route a where a.departure = :arr";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("arr",arr);
        //query.setCacheable(true);
        List<Route> routes = query.list();
        return routes;
    }

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
