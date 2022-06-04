package model.persistence.dao;


import model.persistence.entity.Orders;

import java.util.List;

public interface OrdersDaoInterface {
    List<Orders> findAll();
    void persist(Orders entity);
    void update(Orders entity);
    void delete(Orders entity);
    void deleteAll();
}
