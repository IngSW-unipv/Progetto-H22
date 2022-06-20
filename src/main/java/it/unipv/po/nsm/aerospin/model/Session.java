package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import model.persistence.entity.User;
import java.sql.Date;

/**
 * Classe Singleton che si occupa della sessione utente
 *
 * @author GruppoNoSuchMethod
 */
public class Session {
    private static Session instance = null;
    private User user;
    private final SimpleBooleanProperty logged = new SimpleBooleanProperty();
    private final SimpleStringProperty loggedButton = new SimpleStringProperty();
    private boolean oneway = true;
    private boolean paid = false;
    private String dep;
    private String ret;
    private Date dateDep;
    private Date dateRet;
    private double price;
    private String cardNumber;

    private Session() {
        logged.set(false);
        loggedButton.set("LOGIN");

        logged.addListener((observable, oldValue, newValue) -> {
            logged.set(newValue);
            if(!newValue) {
                    user = null;
                    loggedButton.set("LOGIN");
                    clear();
            } else {
                    loggedButton.set("ACCOUNT");
            }
        });
    }

    public static Session getInstance() {
        if(instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void clear() {
        oneway = true;
        paid = false;
        dep = null;
        ret = null;
        dateDep = null;
        dateRet = null;
        cardNumber = null;
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

    public SimpleStringProperty getLoggedButton() {
        return loggedButton;
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

    public void setDateDep(Date dateDep) {
        this.dateDep = dateDep;
    }

    public Date getDateRet() {
        return dateRet;
    }

    public void setDateRet(Date dateRet) {
        this.dateRet = dateRet;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
