package util;

import model.persistence.entity.User;

public class Session {

    private User user;
    private boolean logged = false;

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
}
