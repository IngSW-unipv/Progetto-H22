package model.persistence.dao;

import model.persistence.entity.Crew;
import model.persistence.entity.Route;

import java.util.List;

public interface RouteDaoInterface {
    List<Route> findAll();
    List<Route> findById(int id);
    List<Route> findByDepIcao(String dep);
    List<Route> findByDepName(String dep);
    List<Route> findByArr(String arr);
    Route findByDepArr(String dep, String arr);
    boolean checkRoute(String dep, String arr);
    public void persist(Route entity);
    public void update(Route entity);
    public void delete(Route entity);
    public void deleteAll();
}
