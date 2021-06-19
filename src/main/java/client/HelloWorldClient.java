package client;

import entity.Message;
import org.hibernate.Session;
import util.HibernateUtil;

public class HelloWorldClient {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Message message = new Message("Hello World with Hibernate and JPA Annotations 2");

        session.save(message);

        session.getTransaction().commit();
        session.close();

        HibernateUtil.closeSessionFactory();
    }
}

