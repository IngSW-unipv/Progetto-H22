package model.persistence.dao;

import java.util.List;

/**
 * Interfaccia che descrive i metodi utilizzati dalle classi DAO
 *
 * @author GruppoNoSuchMethod
 */
public interface IDao<T> {
    List<T> findAll();
    void persist(T entity);
    void update(T entity);
    void delete(T entity);
    void deleteAll();
}
