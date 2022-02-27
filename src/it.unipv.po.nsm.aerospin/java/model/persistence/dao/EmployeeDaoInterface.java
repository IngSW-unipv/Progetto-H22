package model.persistence.dao;

import model.flight.aircraft.Manufacturer;
import model.persistence.entity.Aircraft;
import model.persistence.entity.Employee;

import java.util.List;

public interface EmployeeDaoInterface {
    List<Employee> findAll();
    List<Employee> findById(int id);
    List<Employee> findByName(String name);
    List<Employee> findBySurname(String surname);
    List<Employee> findByRole(String role);
}
