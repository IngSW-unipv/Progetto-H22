package model.persistence.service;

import model.persistence.dao.BookingDao;
import model.persistence.entity.Booking;
import model.persistence.entity.User;
import java.util.List;

public class BookingService implements IService<Booking> {
    private static BookingDao bookingDao;

    public BookingService() {
        bookingDao = new BookingDao();
    }

    @Override
    public List<Booking> findAll() {
        return null;
    }

    public List<Booking> findByUser(User user) {
        bookingDao.getConn().openCurrentSession();
        return bookingDao.findByUser(user);
    }

    @Override
    public void persist(Booking booking) {
        bookingDao.getConn().openCurrentSessionWithTransaction();
        bookingDao.persist(booking);
        bookingDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Booking booking) {
        bookingDao.getConn().openCurrentSessionWithTransaction();
        bookingDao.update(booking);
        bookingDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(Booking booking) {
        bookingDao.getConn().openCurrentSessionWithTransaction();
        bookingDao.delete(booking);
        bookingDao.getConn().closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteAll() {
        bookingDao.getConn().openCurrentSessionWithTransaction();
        bookingDao.deleteAll();
        bookingDao.getConn().closeCurrentSessionWithTransaction();
    }
}
