package model.person.employee;

import java.util.Date;

public class Employee {
    private String ID;
    private Date hiringDate;
    private double salary;

    public Employee(String ID, String name, String surname, Date hiringDate, double salary) {
        super(name, surname);
        this.ID = ID;
        this.hiringDate = hiringDate;
        this.salary = salary;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
