package model;

import javafx.beans.property.SimpleBooleanProperty;
import model.persistence.entity.User;

import java.sql.Date;
import java.time.LocalDate;

public class Session {

    private User user;
    private final SimpleBooleanProperty logged = new SimpleBooleanProperty();
    private boolean oneway = true;
    private boolean paid = false;
    private String dep;
    private String ret;
    private Date dateDep;
    private Date dateRet;


    public Session () {
        logged.set(false);

        logged.addListener((observable, oldValue, newValue) -> {
            logged.set(newValue);
            if(!newValue)
                user = null;
        });
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        logged.set(true);
        this.user = user;
    }

    public boolean isLogged() {
        return logged.get();
    }

    public void setLogged(boolean logged) {
        this.logged.set(logged);
    }

    public SimpleBooleanProperty loggedProperty() {
        return logged;
    }

    public boolean isOneway() {
        return oneway;
    }

    public void setOneway(boolean oneway) {
        this.oneway = oneway;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public Date getDateDep() {
        return dateDep;
    }

    public void setDateDep(LocalDate dateDep) {
        this.dateDep = Date.valueOf(dateDep);
    }

    public Date getDateRet() {
        return dateRet;
    }

    public void setDateRet(LocalDate dateRet) {
        this.dateRet = Date.valueOf(dateRet);
    }

    public void clear() {
        paid = false;
        dep = null;
        ret = null;
        dateDep = null;
        dateRet = null;
    }
}
