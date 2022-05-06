package model.util.exception;

import org.hibernate.HibernateException;

public class NetworkException extends HibernateException {

    public NetworkException(String message) {
        super(message);
    }
}
