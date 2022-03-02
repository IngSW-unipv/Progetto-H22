package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Crew;
import model.persistence.entity.Role;
import org.hibernate.query.Query;

import java.util.List;

public class RoleDao implements RoleDaoInterface{

    private Connection conn;
    public RoleDao() {
        this.conn = new Connection();
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = (List<Role>) conn.getCurrentSession().createQuery("from Role ").list();
        return roles;
    }

    @Override
    public List<Role> findByRole(String role) {
        String hql = "from Role a where a.role like :role ";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("role","%" + role + "%");
        //query.setCacheable(true);
        List<Role> roles = query.list();
        return roles;
    }
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn){
        this.conn = conn;
    }

}
