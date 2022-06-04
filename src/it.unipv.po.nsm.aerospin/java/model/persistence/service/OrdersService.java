package model.persistence.service;


import model.persistence.dao.OrdersDao;
import model.persistence.entity.Orders;


import java.util.List;

public class OrdersService {
    private static OrdersDao ordersDao;

    public OrdersService() {
        ordersDao = new OrdersDao();
    }

    public List<Orders> findAll() {
        ordersDao.getConn().openCurrentSession();
        List<Orders> orders = ordersDao.findAll();
        ordersDao.getConn().closeCurrentSession();
        return orders;
    }


    public void persist(Orders orders) {
        ordersDao.getConn().openCurrentSessionwithTransaction();
        ordersDao.persist(orders);
        ordersDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void update(Orders orders) {
        ordersDao.getConn().openCurrentSessionwithTransaction();
        ordersDao.update(orders);
        ordersDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void delete(Orders orders) {
        ordersDao.getConn().openCurrentSessionwithTransaction();
        ordersDao.delete(orders);
        ordersDao.getConn().closeCurrentSessionwithTransaction();
    }
    public void deleteAll() {
        ordersDao.getConn().openCurrentSessionwithTransaction();
        ordersDao.deleteAll();
        ordersDao.getConn().closeCurrentSessionwithTransaction();
    }
}
