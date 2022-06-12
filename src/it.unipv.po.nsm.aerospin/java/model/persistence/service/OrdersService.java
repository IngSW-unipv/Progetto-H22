package model.persistence.service;

import model.persistence.dao.OrdersDao;
import model.persistence.entity.Orders;
import model.persistence.entity.User;
import java.util.List;

public class OrdersService implements IService<Orders> {
    private static OrdersDao ordersDao;

    public OrdersService() {
        ordersDao = new OrdersDao();
    }

    @Override
    public List<Orders> findAll() {
        return null;
    }

    public List<Orders> findByUser(User user) {
        ordersDao.getConn().openCurrentSession();
        return ordersDao.findByUser(user);
    }

    @Override
    public void persist(Orders orders) {
        ordersDao.getConn().openCurrentSessionWithTransaction();
        ordersDao.persist(orders);
        ordersDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Orders orders) {
        ordersDao.getConn().openCurrentSessionWithTransaction();
        ordersDao.update(orders);
        ordersDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(Orders orders) {
        ordersDao.getConn().openCurrentSessionWithTransaction();
        ordersDao.delete(orders);
        ordersDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteAll() {
        ordersDao.getConn().openCurrentSessionWithTransaction();
        ordersDao.deleteAll();
        ordersDao.getConn().closeCurrentSessionWithTransaction();
    }
}
