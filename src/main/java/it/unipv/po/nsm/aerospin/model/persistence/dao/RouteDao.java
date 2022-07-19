package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Airport;
import model.persistence.entity.Route;
import model.persistence.entity.User;

import java.util.List;
/**
 * Classe che implementa le query sql nella table Route
 *
 * @author GruppoNoSuchMethod
 */
public class RouteDao implements IDao<Route> {
    private final Connection conn;

    public RouteDao() {
        this.conn = Connection.getInstance();
    }

    public Connection getConn() {
        return conn;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Route> findAll() {
        return(List<Route>) conn.getCurrentSession().createQuery("from Route ").list();
    }

    public Route getRouteByDepArr(String departure, String arrival) {
        String hql = "from Route a where a.routeId = 1088";
        return (Route) conn.getCurrentSession().createQuery(hql)
                .uniqueResult();
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
