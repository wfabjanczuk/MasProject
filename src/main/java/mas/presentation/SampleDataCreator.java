package mas.presentation;

import mas.entity.IceRink;
import mas.entity.SkatingSession;
import mas.entity.Ticket;
import mas.entity.person.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SampleDataCreator {
    private static long peselSeed = 73110167178L;
    private static final long peselInterval = 723627797L;

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();

            Set<Client> clients = getClients();
            clients.forEach(session::persist);

            getTechnicians().forEach(session::persist);
            getTicketCollectors().forEach(session::persist);

            Set<IceRink> iceRinks = getIceRinks();
            iceRinks.forEach(session::persist);

            Set<SkatingSession> skatingSessions = getSkatingSessions(iceRinks, clients);
            skatingSessions.forEach(session::persist);
            clients.forEach(session::persist);

            getTickets(skatingSessions, clients).forEach(session::persist);

            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }

        session.close();
    }

    private static Set<IceRink> getIceRinks() {
        Set<IceRink> iceRinks = new HashSet<>();

        iceRinks.add(new IceRink(
                "Hockey rink",
                "200 by 85 feet (60.96 m x 25.9 m) with a corner radius of 28 feet (8.5 m)",
                1500
        ));
        iceRinks.add(new IceRink(
                "Public rink",
                "30 by 40 meters",
                1200
        ));
        iceRinks.add(new IceRink(
                "Children's rink",
                "15 by 30 meters",
                450
        ));

        return iceRinks;
    }

    private static Set<Person> getPersonObjects() {
        String[] names = {"Andrzej", "Barbara", "Celina", "Damian", "Edmund", "Ferdynand", "Gabriela", "Halina", "Iga", "Jan", "Karolina", "Lech", "Magdalena", "Natalia", "Olaf", "Patryk", "Robert", "Urban", "Wiktoria", "Zenon"};

        return Arrays.stream(names).map(firstname -> {
            String firstLetter = firstname.substring(0, 1),
                    lastname = firstLetter + new StringBuilder(firstname.toLowerCase()).reverse(),
                    email = firstname.toLowerCase() + "@gmail.com";

            int dayOfMonth = 1 + (int) (Math.random() * 30);
            Date birthdate = java.sql.Date.valueOf("2000-01-" + dayOfMonth);

            return new Person(email, firstname, lastname, birthdate);
        }).collect(Collectors.toSet());
    }

    private static Set<Client> getClients() {
        return getPersonObjects()
                .stream()
                .filter(person -> person.getFirstname().substring(0, 1).compareToIgnoreCase("K") < 0)
                .map(person -> {
                    boolean marketingConsent = ((int) (Math.random() * 2)) > 0;
                    int discountPercent = ((int) (Math.random() * 3)) * 5;

                    return new Client(person, marketingConsent, discountPercent);
                })
                .collect(Collectors.toSet());
    }

    private static Stream<Employee> getEmployeeObjectStream() {
        return getPersonObjects()
                .stream()
                .filter(person -> person.getFirstname().substring(0, 1).compareToIgnoreCase("K") >= 0)
                .map(person -> {
                    int dayOfMonth = 1 + (int) (Math.random() * 30),
                            buildingNumber = 1 + (int) (Math.random() * 255),
                            salaryPrefix = 2 + (int) (Math.random() * 4),
                            telephoneSuffix = (int) (Math.random() * 9);
                    String address = "Koszykowa " + buildingNumber + ", 02-008 Warszawa",
                            telephone = "22584450" + telephoneSuffix;
                    Date employmentDate = java.sql.Date.valueOf("2000-01-" + dayOfMonth);
                    BigDecimal salary = new BigDecimal(salaryPrefix + "000");

                    return new Employee(
                            person,
                            getNextPesel(),
                            address,
                            telephone,
                            employmentDate,
                            salary
                    );
                });
    }

    private static String getNextPesel() {
        peselSeed += peselInterval;
        return String.valueOf(Math.abs(peselSeed)).substring(0, 11);
    }

    private static Set<Technician> getTechnicians() {
        return getEmployeeObjectStream()
                .filter(employee -> employee.getPerson().getFirstname().substring(0, 1).compareToIgnoreCase("P") < 0)
                .map(employee -> {
                    ArrayList<String> skills = new ArrayList<>();

                    int skillsOption = (int) (Math.random() * 4);

                    if (skillsOption > 0) {
                        skills.add("sharpening");
                    }
                    if (skillsOption > 1) {
                        skills.add("replacing parts");
                    }
                    if (skillsOption > 2) {
                        skills.add("repairing");
                    }

                    return new Technician(employee, skills);
                })
                .collect(Collectors.toSet());
    }

    private static Set<TicketCollector> getTicketCollectors() {
        return getEmployeeObjectStream()
                .filter(employee -> employee.getPerson().getFirstname().substring(0, 1).compareToIgnoreCase("P") >= 0)
                .map(employee -> {
                    boolean isAllowedToSell = ((int) (Math.random() * 2)) > 0;

                    return new TicketCollector(employee, isAllowedToSell);
                })
                .collect(Collectors.toSet());
    }

    private static Set<SkatingSession> getSkatingSessions(Set<IceRink> iceRinkSet, Set<Client> clients) {
        Set<SkatingSession> skatingSessionSet = new HashSet<>();

        Calendar calendarIterator = Calendar.getInstance();
        Calendar calendarMax = Calendar.getInstance();

        calendarIterator.setTime(java.sql.Date.valueOf("2021-06-01"));
        calendarMax.setTime(java.sql.Date.valueOf("2021-06-03"));

        while (calendarIterator.before(calendarMax)) {
            Calendar calendarSessionEnd = (Calendar) calendarIterator.clone();
            calendarSessionEnd.add(Calendar.HOUR_OF_DAY, 1);

            Date dateSessionStart = calendarIterator.getTime();
            Date dateSessionEnd = calendarSessionEnd.getTime();

            iceRinkSet.forEach(iceRink -> skatingSessionSet.add(new SkatingSession(
                    dateSessionStart,
                    dateSessionEnd,
                    new BigDecimal("10.00"),
                    DescriptionGenerator.generateText(200),
                    (int) (Math.random() * 2) > 0,
                    (int) (Math.random() * 2) > 0,
                    iceRink
            )));

            calendarIterator = calendarSessionEnd;
        }

        skatingSessionSet.stream()
                .filter(SkatingSession::getRegular)
                .forEach(skatingSession -> skatingSession.setDaysOfWeek(getRandomDaysOfWeek()));

        skatingSessionSet.stream()
                .filter(SkatingSession::getPrivate)
                .forEach(skatingSession -> clients.stream()
                        .filter(c -> (int) (Math.random() * 2) > 0)
                        .forEach(c -> c.getPrivateSkatingSessions().add(skatingSession))
                );

        return skatingSessionSet;
    }

    private static Set<Integer> getRandomDaysOfWeek() {
        Set<Integer> daysOfWeek = new HashSet<>();

        for (int i = 0; i < 7; i++) {
            boolean isChosen = (int) (Math.random() * 2) > 0 || daysOfWeek.isEmpty();
            if (isChosen) {
                daysOfWeek.add(i);
            }
        }

        return daysOfWeek;
    }

    private static Set<Ticket> getTickets(Set<SkatingSession> skatingSessions, Set<Client> clients) {
        Set<Ticket> ticketSet = new HashSet<>();

        skatingSessions.forEach(skatingSession -> clients.stream()
                .filter(c -> (int) (Math.random() * 3) == 0)
                .forEach(client -> {
                    int dayOfMonth = 1 + (int) (Math.random() * 30);
                    Date dateSold = java.sql.Date.valueOf("2021-05-" + dayOfMonth);

                    ticketSet.add(new Ticket(dateSold, client, skatingSession));
                })
        );

        return ticketSet;
    }
}
