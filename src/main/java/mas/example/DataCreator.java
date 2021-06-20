package mas.example;

import mas.entity.Client;
import mas.entity.Employee;
import mas.entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataCreator {
    private static Session session;

    private static long peselSeed = 73110167178L;
    private static final long peselInterval = 723627797L;

    private static String getNextPesel() {
        peselSeed += peselInterval;
        return String.valueOf(Math.abs(peselSeed)).substring(0, 11);
    }

    public static void main(String[] args) {
        session = HibernateUtil.getSessionFactory().openSession();

        createClientRows();
        createEmployeeRows();

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

    private static void createClientRows() {
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();

            getClientObjects().forEach(session::persist);

            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }

    private static Set<Client> getClientObjects() {
        return getPersonObjectStreamForClients()
                .map(person -> new Client(person, false, 0))
                .collect(Collectors.toSet());
    }

    private static Stream<Person> getPersonObjectStreamForClients() {
        return createPersonObjects()
                .stream()
                .filter(person -> person.getFirstname().substring(0, 1).compareToIgnoreCase("K") < 0);
    }

    private static void createEmployeeRows() {
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();

            getEmployeeObjects().forEach(session::persist);

            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }

    private static Set<Employee> getEmployeeObjects() {
        return getPersonObjectStreamForEmployees()
                .map(person -> {
                    int dayOfMonth = 1 + (int) (Math.random() * 30),
                            buildingNumber = 1 + (int) (Math.random() * 255),
                            salaryPrefix = 2 + (int) (Math.random() * 4),
                            telephoneSuffix = (int) (Math.random() * 9);
                    String address = "Koszykowa " + buildingNumber + ", 02-008 Warszawa",
                            telephone = "22584450" + telephoneSuffix;
                    Date employmentDate = Date.valueOf("2000-01-" + dayOfMonth);
                    BigDecimal salary = new BigDecimal(salaryPrefix + "000");

                    return new Employee(
                            person,
                            getNextPesel(),
                            address,
                            telephone,
                            employmentDate,
                            salary
                    );
                })
                .collect(Collectors.toSet());
    }

    private static Stream<Person> getPersonObjectStreamForEmployees() {
        return createPersonObjects()
                .stream()
                .filter(person -> person.getFirstname().substring(0, 1).compareToIgnoreCase("K") >= 0);
    }
}
