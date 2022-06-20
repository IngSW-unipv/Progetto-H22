package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Booking;
import model.persistence.entity.User;

import java.util.List;

/**
 * Classe che implementa le query sql verso la table Booking
 *
 * @author GruppoNoSuchMethod
 */
public class BookingDao implements IDao<Booking> {
    private final Connection conn;

    public BookingDao() {
        this.conn = Connection.getInstance();
    }

    public Connection getConn() {
        return conn;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Booking> findAll() {
        return (List<Booking>) conn.getCurrentSession().createQuery("from Booking ").list();
    }

    @SuppressWarnings("unchecked")
    public List<Booking> findByUser(User user) {
        String hql = "from Booking o where o.passengerByPassengerId.userByUserId.id = :user";
        return (List<Booking>) conn.getCurrentSession().createQuery(hql)
                .setParameter("user", user.getId()).list();
    }

    @Override
    public void persist(Booking entity) {
        conn.getCurrentSession().save(entity);
    }

    @Override
    public void update(Booking entity) {
        conn.getCurrentSession().update(entity);
    }

    @Override
    public void delete(Booking entity) {
        conn.getCurrentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<Booking> entityList = findAll();
        for (Booking entity : entityList) {
            delete(entity);
        }
    }
}
