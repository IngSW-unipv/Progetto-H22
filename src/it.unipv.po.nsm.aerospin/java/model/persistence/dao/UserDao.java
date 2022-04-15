package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.daoInterface.UserDaoInterface;
import model.persistence.entity.User;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao implements UserDaoInterface {

    private Connection conn;

    public UserDao() {
        this.conn = new Connection();
    }

    @Override
    public List<User> findAll() {
        List<User> users = (List<User>) conn.getCurrentSession().createQuery("from User ").list();
        return  users;
    }

    @Override
    public List<User> findByEmail(String email) {
        String hql = "from User a where a.email = :email";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("email",email);
        //query.setCacheable(true);
        List<User> users = query.list();
        return users;
    }



    @Override
    public void persist(User entity) {
        conn.getCurrentSession().save(entity);
    }

    @Override
    public void update(User entity) {
        conn.getCurrentSession().update(entity);
    }

    @Override
    public void delete(User entity) {
        conn.getCurrentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<User> entityList = findAll();
        for (User entity : entityList) {
            delete(entity);
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn){
        this.conn = conn;
    }

}
