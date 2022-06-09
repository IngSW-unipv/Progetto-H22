package model.persistence.service;

import model.persistence.dao.PassengerDao;
import model.persistence.entity.Passenger;
import java.util.List;

public class PassengerService implements IService<Passenger> {
    private static PassengerDao passengerDao;

    public PassengerService() {
        passengerDao = new PassengerDao();
    }

    @Override
    public List<Passenger> findAll() {
        passengerDao.getConn().openCurrentSession();
        List<Passenger> passengers = passengerDao.findAll();
        passengerDao.getConn().closeCurrentSession();
        return passengers;
    }

    @Override
    public void persist(Passenger passenger) {
        passengerDao.getConn().openCurrentSessionwithTransaction();
        passengerDao.persist(passenger);
        passengerDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void update(Passenger passenger) {
        passengerDao.getConn().openCurrentSessionwithTransaction();
        passengerDao.update(passenger);
        passengerDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void delete(Passenger passenger) {
        passengerDao.getConn().openCurrentSessionwithTransaction();
        passengerDao.delete(passenger);
        passengerDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteAll() {
        passengerDao.getConn().openCurrentSessionwithTransaction();
        passengerDao.deleteAll();
        passengerDao.getConn().closeCurrentSessionwithTransaction();
    }
}
