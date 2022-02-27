package model.persistence.dao;

import model.persistence.entity.Route;

import java.util.List;

public interface RouteDaoInterface {
    public void persist(Route entity);
    public void update(Route entity);
    public Route findById(String id);
    public void delete(Route route);
    public List<Route> findAll();
    public void deleteAll();
}
