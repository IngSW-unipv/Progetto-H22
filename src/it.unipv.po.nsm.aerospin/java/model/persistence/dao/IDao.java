package model.persistence.dao;

import java.util.List;

/**
 * Interfaccia per richiamare i metodi implementati nelle classi di gestione delle query del Database. Viene utilizzato il pattern DAO.
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
