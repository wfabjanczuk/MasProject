package mas.repository;

import org.hibernate.Session;
import util.HibernateUtil;

abstract public class Repository {
    protected static Session session;

    public Repository() {
        startSessionIfClosed();
    }

    protected void startSessionIfClosed() {
        if (session != null && session.isOpen()) {
            return;
        }
        session = HibernateUtil.getSessionFactory().openSession();
    }

    protected void resetSession() {
        closeSession();
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void closeSession() {
        if (session != null) {
            session.close();
        }
        session = null;
    }
}
