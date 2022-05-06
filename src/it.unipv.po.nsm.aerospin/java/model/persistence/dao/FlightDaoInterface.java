package model.persistence.dao;

import model.persistence.entity.Flight;


import java.sql.Date;
import java.util.List;

public interface FlightDaoInterface {
    List<Flight> findAll();
    void persist(Flight entity);
    void update(Flight entity);
    void delete(Flight entity);
    void deleteAll();
}
