package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Passenger;
import java.util.List;

/**
 * Classe che implementa le query del database tramite dei metodi. Viene utilizzato il pattern DAO.
 *
 * @author GruppoNoSuchMethod
 */
public class PassengerDao implements IDao<Passenger> {
    private final Connection conn;

    public PassengerDao() {
        this.conn = new Connection();
    }

    public Connection getConn(){
        return conn;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Passenger> findAll() {
        return (List<Passenger>) conn.getCurrentSession().createQuery("from Passenger ").list();
    }

    @Override
    public void persist(Passenger entity) {
        conn.getCurrentSession().save(entity);
    }

    @Override
    public void update(Passenger entity) {
        conn.getCurrentSession().update(entity);
    }

    @Override
    public void delete(Passenger entity) {
        conn.getCurrentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<Passenger> entityList = findAll();
        for (Passenger entity : entityList) {
            delete(entity);
        }
    }
}
