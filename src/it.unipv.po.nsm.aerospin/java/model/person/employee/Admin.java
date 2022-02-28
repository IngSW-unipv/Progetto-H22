package model.person.employee;

import model.persistence.entity.Employee;
import model.person.IAccess;

import java.util.Date;

public class Admin extends Employee implements IAccess {

    public String email;
    public String pwd;

    public Admin(String email, String pwd, String ID, String name, String surname, Date hiringDate, double salary) {
        this.email = email;
        this.pwd = pwd;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPwd() {
        return pwd;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
