package model.persistence.dao;

import model.persistence.entity.Route;
import java.util.List;

public interface RouteDaoInterface {
    List<Route> findAll();
    void persist(Route entity);
    void update(Route entity);
    void delete(Route entity);
    void deleteAll();
}
