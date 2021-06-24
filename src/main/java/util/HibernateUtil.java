package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();
    private static final String configFilename = "hibernate.cfg.xml";

    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry serviceRegistry;
            Metadata metadata;

            serviceRegistry = new StandardServiceRegistryBuilder().configure(configFilename).build();
            metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable exception) {
            System.err.println("Initial SessionFactory creation failed." + exception);

            throw new ExceptionInInitializerError(exception);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
        sessionFactory = null;
    }
}



