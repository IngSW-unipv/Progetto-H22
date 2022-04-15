package model.persistence.daoInterface;


import model.persistence.entity.Employee;

import java.util.List;

public interface EmployeeDaoInterface {
    List<Employee> findAll();
    List<Employee> findById(int id);
    List<Employee> findByName(String name);
    List<Employee> findBySurname(String surname);
    List<Employee> findByRole(String role);
    public void persist(Employee entity);
    public void update(Employee entity);
    public void delete(Employee entity);
    public void deleteAll();
}
