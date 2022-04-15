package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.daoInterface.EmployeeDaoInterface;
import model.persistence.entity.Employee;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDao implements EmployeeDaoInterface {

    private Connection conn;

    public EmployeeDao() {
        this.conn = new Connection();
    }
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = (List<Employee>) conn.getCurrentSession().createQuery("from Employee ").list();
        return  employees;
    }

    @Override
    public List<Employee> findById(int id) {
        String hql = "from Employee a where a.id = :id";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        //query.setCacheable(true);
        List<Employee> employees = query.list();
        return   employees;
    }

    @Override
    public List<Employee> findByName(String name) {
        String hql = "from Employee a where a.name like :name ";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("name","%" + name + "%");
        //query.setCacheable(true);
        List<Employee> employees = query.list();
        return employees;
    }

    @Override
    public List<Employee> findBySurname(String surname) {
        String hql = "from Employee a where a.name like :surname ";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("surname","%" + surname + "%");
        //query.setCacheable(true);
        List<Employee> employees = query.list();
        return employees;
    }

    @Override
    public List<Employee> findByRole(String role) {
        String hql = "from Employee a where a.role = :role";
        Query query = conn.getCurrentSession().createQuery(hql);
        query.setParameter("role",role);
        //query.setCacheable(true);
        List<Employee> employees = query.list();
        return employees;
    }

    @Override
    public void persist(Employee entity) {
        conn.getCurrentSession().save(entity);
    }

    @Override
    public void update(Employee entity) {
        conn.getCurrentSession().update(entity);
    }

    @Override
    public void delete(Employee entity) {
        conn.getCurrentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<Employee> entityList = findAll();
        for (Employee entity : entityList) {
            delete(entity);
        }

    }
}
