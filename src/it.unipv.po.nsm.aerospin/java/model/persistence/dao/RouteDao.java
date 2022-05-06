package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Route;
import org.hibernate.query.Query;

import java.util.List;

public class RouteDao implements RouteDaoInterface {

    private Connection conn;

    public RouteDao() {
        this.conn = new Connection();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Route> findAll() {
        String query = "from Route";
        Query q = conn.getCurrentSession().createQuery(query);
        List<Route> routes = (List<Route>) q.list();
        return  routes;
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
}
