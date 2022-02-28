package model.persistence.dao;

import model.persistence.entity.Crew;
import model.persistence.entity.Route;

import java.util.List;

public interface RouteDaoInterface {
    List<Route> findAll();
    List<Route> findById(int id);
    List<Route> findByDep(String dep);
    List<Route> findByArr(String arr);
    public void persist(Route entity);
    public void update(Route entity);
    public void delete(Route entity);
    public void deleteAll();
}
