package model.persistence.daoInterface;


import model.persistence.entity.Crew;

import java.util.List;

public interface CrewDaoInterface {
    List<Crew> findAll();
    List<Crew> findById(int id);
    List<Crew> findByCaptain(String captainName);
    public void persist(Crew entity);
    public void update(Crew entity);
    public void delete(Crew entity);
    public void deleteAll();
}
