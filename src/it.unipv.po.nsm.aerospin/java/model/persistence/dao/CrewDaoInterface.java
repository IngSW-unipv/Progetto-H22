package model.persistence.dao;


import model.persistence.entity.Crew;

import java.util.List;

public interface CrewDaoInterface {
    List<Crew> findAll();
    List<Crew> findById(int id);
    List<Crew> findByCaptain(String captainName);
}
