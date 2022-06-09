package model.persistence.service;

import model.persistence.dao.UserDao;
import model.persistence.entity.User;
import java.util.List;

public class UserService implements IService<User> {
    private static UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    public User findByEmail(String email) {
        userDao.getConn().openCurrentSession();
        User user = userDao.findByEmail(email);
        userDao.getConn().closeCurrentSession();
        return user;
    }

    @Override
    public void persist(User user) {
        userDao.getConn().openCurrentSessionwithTransaction();
        userDao.persist(user);
        userDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void update(User user) {
        userDao.getConn().openCurrentSessionwithTransaction();
        userDao.update(user);
        userDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void delete(User user) {
        userDao.getConn().openCurrentSessionwithTransaction();
        userDao.delete(user);
        userDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteAll() {
        userDao.getConn().openCurrentSessionwithTransaction();
        userDao.deleteAll();
        userDao.getConn().closeCurrentSessionwithTransaction();
    }
}
