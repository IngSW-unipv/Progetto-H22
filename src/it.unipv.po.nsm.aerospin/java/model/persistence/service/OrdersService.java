package model.persistence.service;

import model.persistence.dao.OrdersDao;
import model.persistence.entity.Booking;
import model.persistence.entity.User;
import java.util.List;

public class OrdersService implements IService<Booking> {
    private static OrdersDao ordersDao;

    public OrdersService() {
        ordersDao = new OrdersDao();
    }

    @Override
    public List<Booking> findAll() {
        return null;
    }

    public List<Booking> findByUser(User user) {
        ordersDao.getConn().openCurrentSession();
        return ordersDao.findByUser(user);
    }

    @Override
    public void persist(Booking booking) {
        ordersDao.getConn().openCurrentSessionWithTransaction();
        ordersDao.persist(booking);
        ordersDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Booking booking) {
        ordersDao.getConn().openCurrentSessionWithTransaction();
        ordersDao.update(booking);
        ordersDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(Booking booking) {
        ordersDao.getConn().openCurrentSessionWithTransaction();
        ordersDao.delete(booking);
        ordersDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteAll() {
        ordersDao.getConn().openCurrentSessionWithTransaction();
        ordersDao.deleteAll();
        ordersDao.getConn().closeCurrentSessionWithTransaction();
    }
}
