package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Orders;
import model.persistence.service.OrdersService;

import javax.persistence.Query;
import java.util.List;

public class OrdersDao implements OrdersDaoInterface {

    private Connection conn;

    public OrdersDao() {
        this.conn = new Connection();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Orders> findAll() {
        return (List<Orders>) conn.getCurrentSession().createQuery("from Orders ").list();
    }





    @Override
    public void persist(Orders entity) {
        conn.getCurrentSession().save(entity);
    }

    @Override
    public void update(Orders entity) {
        conn.getCurrentSession().update(entity);
    }

    @Override
    public void delete(Orders entity) {
        conn.getCurrentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<Orders> entityList = findAll();
        for (Orders entity : entityList) {
            delete(entity);
        }

    }
}
