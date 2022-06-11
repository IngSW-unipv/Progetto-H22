package model.exception;

import org.hibernate.HibernateException;

/**
 * Classe che si occupa di gestire l'eccezione NetworkException.
 *
 * @author GruppoNoSuchMethod
 */
public class NetworkException extends HibernateException {

    public NetworkException(String message) {
        super(message);
    }
}
