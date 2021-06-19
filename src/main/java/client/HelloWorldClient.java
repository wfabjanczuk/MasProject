package client;

import entity.Message;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class HelloWorldClient {
    public static void main(String[] args) {
        createMessage();

        HibernateUtil.closeSessionFactory();
    }

    private static void createMessage() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Message message = new Message("Hello World with logging");

        session.save(message);

        session.getTransaction().commit();
        session.close();
    }

    private static void getMessage(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.getTransaction();

        try {
            txn.begin();
            Message msg = (Message) session.get(Message.class, id);
            txn.commit();
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private static void editMessage(long id, String newText) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.getTransaction();

        try {
            txn.begin();

            Message msg = (Message) session.get(Message.class, id);
            msg.setText(newText);
            session.save(msg);

            txn.commit();
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private static void deleteMessage(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.getTransaction();

        try {
            txn.begin();
            Message msg = (Message) session.get(Message.class, id);
            session.delete(msg);
            txn.commit();
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

