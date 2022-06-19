package model.persistence.service;

import java.util.List;

/**
 * Interfaccia Service che richiama le principali azioni disponibili nell'applicativo.
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
