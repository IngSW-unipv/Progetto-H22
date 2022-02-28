package model.persistence.service;

import model.persistence.dao.RouteDao;
import model.persistence.entity.Passenger;
import model.persistence.entity.Route;

import java.util.List;

public class RouteService {

    private static RouteDao routeDao;

    public RouteService() {
         routeDao= new RouteDao();
    }

    public List<Route> findAll() {
        routeDao.getConn().openCurrentSession();
        List<Route> routes = routeDao.findAll();
        routeDao.getConn().closeCurrentSession();
        return routes;
    }

    public List<Route> findById(int id) {
        routeDao.getConn().openCurrentSession();
        List<Route> routes = routeDao.findById(id);
        routeDao.getConn().closeCurrentSession();
        return routes;
    }

    public List<Route> findByDep(String dep) {
        routeDao.getConn().openCurrentSession();
        List<Route> routes = routeDao.findByDep(dep);
        routeDao.getConn().closeCurrentSession();
        return routes;
    }

    public List<Route> findByArr(String arr) {
        routeDao.getConn().openCurrentSession();
        List<Route> routes = routeDao.findByArr(arr);
        routeDao.getConn().closeCurrentSession();
        return routes;
    }

    public void persist(Route route) {
        routeDao.getConn().openCurrentSessionwithTransaction();
        routeDao.persist(route);
        routeDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void update(Route route) {
        routeDao.getConn().openCurrentSessionwithTransaction();
        routeDao.update(route);
        routeDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void delete(Route route) {
        routeDao.getConn().openCurrentSessionwithTransaction();
        routeDao.delete(route);
        routeDao.getConn().closeCurrentSessionwithTransaction();
    }
    public void deleteAll() {
        routeDao.getConn().openCurrentSessionwithTransaction();
        routeDao.deleteAll();
        routeDao.getConn().closeCurrentSessionwithTransaction();
    }
}
