package model.persistence.service;

import model.exception.NoMatchException;
import model.persistence.dao.UserDao;
import model.persistence.entity.User;
import java.util.List;

/**
 * Servizio che astrae la complessità del servizio sottostante di User
 *
 * @author GruppoNoSuchMethod
 */
public class UserService implements IService<User> {
    private static UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    @Override
    public List<User> findAll() {
        userDao.getConn().openCurrentSession();
        List<User> user = userDao.findAll();
        userDao.getConn().closeCurrentSession();
        return user;
    }

    public User findByEmail(String email) throws NoMatchException {
        userDao.getConn().openCurrentSession();
        User user = userDao.findByEmail(email);
        userDao.getConn().closeCurrentSession();
        if(user != null) {
            return user;
        } else {
            throw new NoMatchException("Not Matched!\n");
        }
    }

    @Override
    public void persist(User user) {
        userDao.getConn().openCurrentSessionWithTransaction();
        userDao.persist(user);
        userDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(User user) {
        userDao.getConn().openCurrentSessionWithTransaction();
        userDao.update(user);
        userDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(User user) {
        userDao.getConn().openCurrentSessionWithTransaction();
        userDao.delete(user);
        userDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteAll() {
        userDao.getConn().openCurrentSessionWithTransaction();
        userDao.deleteAll();
        userDao.getConn().closeCurrentSessionWithTransaction();
    }
}
