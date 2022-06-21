package model.persistence.service;

import java.util.List;

/**
 * Interfaccia che descrive i metodi utilizzati dai Servizi
 *
 * @author GruppoNoSuchMethod
 */
public interface IService<T> {
    List<T> findAll();
    void persist(T entity);
    void update(T entity);
    void delete(T entity);
    void deleteAll();
}
