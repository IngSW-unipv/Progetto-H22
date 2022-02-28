package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Crew;
import org.hibernate.query.Query;

import java.util.List;

public class CrewDao implements CrewDaoInterface{
    private Connection conn;

    public CrewDao() {
        this.conn = new Connection();
    }

    @Override
    public List<Crew> findAll() {
        List<Crew> crews = (List<Crew>) conn.getCurrentSession().createQuery("from Airport ").list();
        return  crews;
    }

    @Override
    public List<Crew> findById(int id) {
        String hql = "from Crew a where a.id = :id";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        //query.setCacheable(true);
        List<Crew> crews = query.list();
        return crews;
    }

    @Override
    public List<Crew> findByCaptain(String captainName) {
        String hql = "from Crew a where a.role like :name ";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("name","%" + captainName + "%");
        //query.setCacheable(true);
        List<Crew> crews = query.list();
        return crews;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn){
        this.conn = conn;
    }
}
