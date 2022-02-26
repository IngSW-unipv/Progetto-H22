package model.person.employee;

import model.person.IAccess;

import java.util.Date;

public class Admin extends Employee implements IAccess {

    public String email;
    public String pwd;

    public Admin(String email, String pwd, String ID, String name, String surname, Date hiringDate, double salary) {
        super(ID, name, surname, hiringDate, salary);
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
