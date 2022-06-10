package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.User;
import java.util.List;

public class UserDao implements IDao<User> {
    private final Connection conn;

    public UserDao() {
        this.conn = new Connection();
    }

    public Connection getConn() {
        return conn;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return (List<User>) conn.getCurrentSession().createQuery("from User ").list();
    }

    public User findByEmail(String email) {
        String hql = "from User a where a.email = :email";
        return (User) conn.getCurrentSession().createQuery(hql)
                .setParameter("email", email).uniqueResult();
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
}
