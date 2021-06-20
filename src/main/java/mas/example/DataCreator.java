package mas.example;

import mas.entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.Date;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class DataCreator {
    private static Session session;

    public static void main(String[] args) {
        session = HibernateUtil.getSessionFactory().openSession();

        createPersonRows();

        session.close();
        HibernateUtil.getSessionFactory().close();
    }

    private static void createPersonRows() {
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();

            createPersonObjects().forEach(session::persist);

            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }

    private static Set<Person> createPersonObjects() {
        String[] names = {"Andrzej", "Barbara", "Celina", "Damian", "Edmund", "Ferdynand", "Gabriela", "Halina", "Iga", "Jan", "Karolina", "Lech", "Magdalena", "Natalia", "Olaf", "Patryk", "Robert", "Urban", "Wiktoria", "Zenon"};

        return Arrays.stream(names).map(firstname -> {
            String firstLetter = firstname.substring(0, 1),
                    lastname = firstLetter + new StringBuilder(firstname.toLowerCase()).reverse(),
                    email = firstname.toLowerCase() + "@gmail.com";

            int dayOfMonth = 1 + (int) (Math.random() * 30);
            Date birthdate = Date.valueOf("2000-01-" + dayOfMonth);

            return new Person(email, firstname, lastname, birthdate);
        }).collect(Collectors.toSet());
    }
}
