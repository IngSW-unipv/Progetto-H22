package model.persistence.service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
    void persist(T entity);
    void update(T entity);
    void delete(T entity);
    void deleteAll();
}
