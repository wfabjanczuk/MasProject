package mas.presentation;

import mas.entity.person.Person;
import mas.service.PersonService;
import org.hibernate.Session;
import util.HibernateUtil;

public class Test {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Person person = session.get(Person.class, 1);
        System.out.println(person.getFirstname());
        System.out.println(person.getLastname());
        System.out.println(PersonService.getAge(person));
        System.out.println(person.getClient());
        System.out.println(person.getEmployee());

        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}
