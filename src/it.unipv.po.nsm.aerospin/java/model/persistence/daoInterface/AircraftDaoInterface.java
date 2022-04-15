package model.persistence.daoInterface;

import model.flight.aircraft.Manufacturer;
import model.persistence.entity.Aircraft;

import java.util.List;

public interface AircraftDaoInterface {
    List<Aircraft> findAll();
    List<Aircraft> findByMan(Manufacturer manufacturer);
    List<Aircraft> findByModel(String model);
    List<Aircraft> findByTailNumber(int tailNumber);
    List<Aircraft> findAvailable();
    public void persist(Aircraft entity);
    public void update(Aircraft entity);
    public void delete(Aircraft entity);
    public void deleteAll();
}
