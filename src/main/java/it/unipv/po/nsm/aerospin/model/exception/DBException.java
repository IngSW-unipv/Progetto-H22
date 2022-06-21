package model.exception;

import org.hibernate.HibernateException;

/**
 * Classe che si occupa di gestire l'eccezione DBException
 *
 * @author GruppoNoSuchMethod
 */
public class DBException extends HibernateException {

    public DBException(String message) {
        super(message);
    }
}
