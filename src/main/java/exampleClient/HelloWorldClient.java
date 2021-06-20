package exampleClient;

import exampleEntity.Customer;
import exampleEntity.Message;
import exampleEntity.Passport;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class HelloWorldClient {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();

            Passport passport = new Passport("925076473");
            Customer customer = new Customer("Nicole Scott", passport);

            session.persist(customer);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }


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

