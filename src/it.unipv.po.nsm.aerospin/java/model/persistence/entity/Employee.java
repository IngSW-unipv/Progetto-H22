package model.persistence.entity;
import org.hibernate.annotations.Cache;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Employee implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "employeeId", nullable = false)
    private int employeeId;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "surname", nullable = false, length = 100)
    private String surname;
    @Basic
    @Column(name = "role", nullable = true, length = 15)
    private String role;
    @Basic
    @Column(name = "hiringDate", nullable = false)
    private Date hiringDate;
    @Basic
    @Column(name = "salary", nullable = true, precision = 0)
    private Double salary;
    @OneToOne(mappedBy = "employeeByCrewId")
    private Crew crewByEmployeeId;
    @OneToMany(mappedBy = "employeeByEmployeeId")
    private Collection<Crew> crewsByEmployeeId;
    @OneToMany(mappedBy = "employeeByEmployeeId")
    private Collection<User> usersByEmployeeId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (employeeId != employee.employeeId) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (surname != null ? !surname.equals(employee.surname) : employee.surname != null) return false;
        if (role != null ? !role.equals(employee.role) : employee.role != null) return false;
        if (hiringDate != null ? !hiringDate.equals(employee.hiringDate) : employee.hiringDate != null) return false;
        if (salary != null ? !salary.equals(employee.salary) : employee.salary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (hiringDate != null ? hiringDate.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }

    public Crew getCrewByEmployeeId() {
        return crewByEmployeeId;
    }

    public void setCrewByEmployeeId(Crew crewByEmployeeId) {
        this.crewByEmployeeId = crewByEmployeeId;
    }

    public Collection<Crew> getCrewsByEmployeeId() {
        return crewsByEmployeeId;
    }

    public void setCrewsByEmployeeId(Collection<Crew> crewsByEmployeeId) {
        this.crewsByEmployeeId = crewsByEmployeeId;
    }

    public Collection<User> getUsersByEmployeeId() {
        return usersByEmployeeId;
    }

    public void setUsersByEmployeeId(Collection<User> usersByEmployeeId) {
        this.usersByEmployeeId = usersByEmployeeId;
    }
}
