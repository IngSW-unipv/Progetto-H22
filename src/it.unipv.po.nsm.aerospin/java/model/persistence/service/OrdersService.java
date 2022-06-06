package model.persistence.service;


import model.persistence.dao.OrdersDao;
import model.persistence.entity.Orders;


import java.util.List;
import java.util.Objects;

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

    public List<Orders> findByUserId(int userId) {
        ordersDao.getConn().openCurrentSession();
        List<Orders> orders = ordersDao.findAll();
        ordersDao.getConn().closeCurrentSession();
        return orders.stream().filter(o -> o.getPassengerByPassengerId().getUserByUserId().getId() == userId).collect(java.util.stream.Collectors.toList());
    }





    public List<Orders> findByEmail(String email) {
        ordersDao.getConn().openCurrentSession();
        List<Orders> orders = ordersDao.findAll();
        ordersDao.getConn().closeCurrentSession();
        return orders.stream().filter(o -> Objects.equals(o.getPassengerByPassengerId().getUserByUserId().getEmail(), email)).collect(java.util.stream.Collectors.toList());
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
