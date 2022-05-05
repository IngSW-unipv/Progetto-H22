package util;

import org.hibernate.HibernateException;

public class ConnectionDBException extends HibernateException {

    public ConnectionDBException(String message) {
        super(message);
    }
}
