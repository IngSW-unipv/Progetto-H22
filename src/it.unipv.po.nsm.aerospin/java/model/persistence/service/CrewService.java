package model.persistence.service;


import model.persistence.dao.CrewDao;
import model.persistence.entity.Airport;
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

    public void persist(Crew crew) {
        crewDao.getConn().openCurrentSessionwithTransaction();
        crewDao.persist(crew);
        crewDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void update(Crew crew) {
        crewDao.getConn().openCurrentSessionwithTransaction();
        crewDao.update(crew);
        crewDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void delete(Crew crew) {
        crewDao.getConn().openCurrentSessionwithTransaction();
        crewDao.delete(crew);
        crewDao.getConn().closeCurrentSessionwithTransaction();
    }
    public void deleteAll() {
        crewDao.getConn().openCurrentSessionwithTransaction();
        crewDao.deleteAll();
        crewDao.getConn().closeCurrentSessionwithTransaction();
    }

}
