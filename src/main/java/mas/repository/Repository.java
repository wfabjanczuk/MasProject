package mas.repository;

import org.hibernate.Session;
import util.HibernateUtil;

abstract public class Repository {
    protected Session session;

    public Repository() {
        resetSession();
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
