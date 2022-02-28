package model.persistence.service;

import model.persistence.dao.EmployeeDao;
import model.persistence.entity.Crew;
import model.persistence.entity.Employee;

import java.util.List;

public class EmployeeService {

    private static EmployeeDao employeeDao;

    public EmployeeService() {
        employeeDao = new EmployeeDao();
    }

    public List<Employee> findAll() {
        employeeDao.getConn().openCurrentSession();
        List<Employee> employees = employeeDao.findAll();
        employeeDao.getConn().closeCurrentSession();
        return employees;
    }
    public List<Employee> findById(int id) {
        employeeDao.getConn().openCurrentSession();
        List<Employee> employees = employeeDao.findById(id);
        employeeDao.getConn().closeCurrentSession();
        return employees;
    }

    public List<Employee> findByName(String name ) {
        employeeDao.getConn().openCurrentSession();
        List<Employee> employees = employeeDao.findByName(name);
        employeeDao.getConn().closeCurrentSession();
        return employees;
    }

    public List<Employee> findBySurname(String surname ) {
        employeeDao.getConn().openCurrentSession();
        List<Employee> employees = employeeDao.findBySurname(surname);
        employeeDao.getConn().closeCurrentSession();
        return employees;
    }

    public List<Employee> findByRole(String role ) {
        employeeDao.getConn().openCurrentSession();
        List<Employee> employees = employeeDao.findByRole(role);
        employeeDao.getConn().closeCurrentSession();
        return employees;
    }

    public void persist(Employee employee) {
        employeeDao.getConn().openCurrentSessionwithTransaction();
        employeeDao.persist(employee);
        employeeDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void update(Employee employee) {
        employeeDao.getConn().openCurrentSessionwithTransaction();
        employeeDao.update(employee);
        employeeDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void delete(Employee employee) {
        employeeDao.getConn().openCurrentSessionwithTransaction();
        employeeDao.delete(employee);
        employeeDao.getConn().closeCurrentSessionwithTransaction();
    }
    public void deleteAll() {
        employeeDao.getConn().openCurrentSessionwithTransaction();
        employeeDao.deleteAll();
        employeeDao.getConn().closeCurrentSessionwithTransaction();
    }


}
