package model.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import model.exception.DBException;

/**
 * Classe che si occupa di gestire la connessione, la sessione e la transazione.
 *
 * @author GruppoNoSuchMethod
 */
public class Connection {
    private Session currentSession;
    private Transaction currentTransaction;
    private static Connection instance = null;

    private Connection() {}

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public void openCurrentSession() {
        currentSession = getSessionFactory().openSession();
    }

    public void openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() throws DBException {
        SessionFactory sessionFactory;
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().configure();
            sessionFactory = configuration.buildSessionFactory(builder.build());
            return sessionFactory;
        } catch (HibernateException e) {
            throw new DBException(e.getLocalizedMessage());
        }
    }

    public Session getCurrentSession() {
        return currentSession;
    }
}
