package model.persistence.service;

import model.persistence.dao.UserDao;
import model.persistence.entity.Passenger;
import model.persistence.entity.User;

import java.util.List;

public class UserService {

    private static UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public List<User> findAll() {
        userDao.getConn().openCurrentSession();
        List<User> users = userDao.findAll();
        userDao.getConn().closeCurrentSession();
        return users;
    }

    public List<User> findById(String email) {
        userDao.getConn().openCurrentSession();
        List<User> users = userDao.findByEmail(email);
        userDao.getConn().closeCurrentSession();
        return users;
    }

    public void persist(User user) {
        userDao.getConn().openCurrentSessionwithTransaction();
        userDao.persist(user);
        userDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void update(User user) {
        userDao.getConn().openCurrentSessionwithTransaction();
        userDao.update(user);
        userDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void delete(User user) {
        userDao.getConn().openCurrentSessionwithTransaction();
        userDao.delete(user);
        userDao.getConn().closeCurrentSessionwithTransaction();
    }
    public void deleteAll() {
        userDao.getConn().openCurrentSessionwithTransaction();
        userDao.deleteAll();
        userDao.getConn().closeCurrentSessionwithTransaction();
    }
}
