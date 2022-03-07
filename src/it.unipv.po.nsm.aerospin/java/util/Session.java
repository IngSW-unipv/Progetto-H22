package util;

import model.persistence.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private User user;
    private boolean logged = false;
    private List<String> info = new ArrayList<>();

    public Session () {
        logged = false;
    }

    public Session(User user) {
        this.user = user;
        logged = true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public List<String> getInfo() {
        return info;
    }

    public void addInfo(String item) {
        this.info.add(item);
    }
}
