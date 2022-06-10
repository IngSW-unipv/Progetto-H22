package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Orders;
import java.util.List;

public class OrdersDao implements IDao<Orders> {
    private final Connection conn;

    public OrdersDao() {
        this.conn = new Connection();
    }

    public Connection getConn() {
        return conn;
    }

    @Override
    @SuppressWarnings("unchecked")
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
