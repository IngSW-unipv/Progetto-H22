package model;

import javafx.beans.property.SimpleBooleanProperty;
import model.booking.Info;
import model.persistence.entity.User;

/**
 * Classe che si occupa della gestione di una sessione utente.
 *
 * @author GruppoNoSuchMethod
 */
public class Session {
    private User user;
    private final SimpleBooleanProperty logged = new SimpleBooleanProperty();
    private Info info;

    public Session () {
        logged.set(false);
        info = new Info();

        logged.addListener((observable, oldValue, newValue) -> {
            logged.set(newValue);
            if(!newValue) {
                user = null;
                clear();
            }
        });
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        logged.set(true);
        this.user = user;
    }

    public Info getInfo() {
        return info;
    }

    public void clear() {
        info = new Info();
    }
}
