package model.persistence.service;

import model.exception.NoMatchException;
import model.persistence.dao.RouteDao;
import model.persistence.entity.Route;
import model.persistence.entity.User;

import java.util.List;

/**
 * Servizio che astrae la complessità del servizio sottostante di Route
 *
 * @author GruppoNoSuchMethod
 */
public class RouteService implements IService<Route> {
    private static RouteDao routeDao;

    public RouteService() {
        routeDao = new RouteDao();
    }

    @Override
    public List<Route> findAll() {
        routeDao.getConn().openCurrentSession();
        List<Route> routes = routeDao.findAll();
        routeDao.getConn().closeCurrentSession();
        return routes;
    }

    public Route getRouteByDepArr(String departure, String arrival) throws NoMatchException {
        routeDao.getConn().openCurrentSession();
        Route route = routeDao.getRouteByDepArr(departure, arrival);
        routeDao.getConn().closeCurrentSession();
        if(route != null) {
            return route;
        } else {
            throw new NoMatchException("Not Matched!\n");
        }
    }

    @Override
    public void persist(Route route) {
        routeDao.getConn().openCurrentSessionWithTransaction();
        routeDao.persist(route);
        routeDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Route route) {
        routeDao.getConn().openCurrentSessionWithTransaction();
        routeDao.update(route);
        routeDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(Route route) {
        routeDao.getConn().openCurrentSessionWithTransaction();
        routeDao.delete(route);
        routeDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteAll() {
        routeDao.getConn().openCurrentSessionWithTransaction();
        routeDao.deleteAll();
        routeDao.getConn().closeCurrentSessionWithTransaction();
    }
}
