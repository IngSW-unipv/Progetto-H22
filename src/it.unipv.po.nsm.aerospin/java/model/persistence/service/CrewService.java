package model.persistence.service;


import model.persistence.dao.CrewDao;
import model.persistence.entity.Crew;

import java.util.List;

public class CrewService {

    private static CrewDao crewDao;

    public CrewService() {
        crewDao = new CrewDao();
    }

    public List<Crew> findAll() {
        crewDao.getConn().openCurrentSession();
        List<Crew> crews = crewDao.findAll();
        crewDao.getConn().closeCurrentSession();
        return crews;
    }

    public List<Crew> findById(int id) {
        crewDao.getConn().openCurrentSession();
        List<Crew> crews = crewDao.findById(id);
        crewDao.getConn().closeCurrentSession();
        return crews;
    }

    public List<Crew> findByCaptain(String name) {
        crewDao.getConn().openCurrentSession();
        List<Crew> crews = crewDao.findByCaptain(name);
        crewDao.getConn().closeCurrentSession();
        return crews;
    }

}
