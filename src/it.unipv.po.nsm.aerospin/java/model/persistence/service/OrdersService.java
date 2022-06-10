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
        List<Orders> orders = ordersDao.findByUser(user);
        ordersDao.getConn().closeCurrentSession();
        return orders;
    }

    @Override
    public void persist(Orders orders) {
        ordersDao.getConn().openCurrentSessionwithTransaction();
        ordersDao.persist(orders);
        ordersDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void update(Orders orders) {
        ordersDao.getConn().openCurrentSessionwithTransaction();
        ordersDao.update(orders);
        ordersDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void delete(Orders orders) {
        ordersDao.getConn().openCurrentSessionwithTransaction();
        ordersDao.delete(orders);
        ordersDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteAll() {
        ordersDao.getConn().openCurrentSessionwithTransaction();
        ordersDao.deleteAll();
        ordersDao.getConn().closeCurrentSessionwithTransaction();
    }
}
