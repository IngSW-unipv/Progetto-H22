package model.util;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import model.persistence.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private User user;
    private SimpleBooleanProperty logged = new SimpleBooleanProperty();
    private List<String> info = new ArrayList<>();
    private boolean oneway = true;



    public Session () {
        logged.set(false);

        logged.addListener((observable, oldValue, newValue) -> {
            // Only if completed
            if (newValue && oldValue)
                logged.set(newValue);
        });
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public List<String> getInfo() {
        return info;
    }

    public void addInfo(String item) {
        this.info.add(item);
    }

    public boolean isOneway() {
        return oneway;
    }

    public void setOneway(boolean oneway) {
        this.oneway = oneway;
    }

}
