package exampleClient;

import exampleEntity.Employee;
import exampleEntity.EmployeeLevel;
import exampleEntity.EmployeeStatus;
import org.hibernate.Session;
import util.HibernateUtil;

public class MappingEnumsClient {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //persisting
        Employee employee1 = new Employee("Josh Stockham", "2014JA11001", EmployeeStatus.FULL_TIME, EmployeeLevel.SENIOR);
        Employee employee2 = new Employee("Ammie Corrio", "2014AI21543", EmployeeStatus.PART_TIME, EmployeeLevel.JUNIOR);
        Employee employee3 = new Employee("Ernie Stenseth", "2014ET25100", EmployeeStatus.CONTRACT, EmployeeLevel.MANAGER);

        session.persist(employee1);
        session.persist(employee2);
        session.persist(employee3);
		
		/*Employee employee = (Employee) session.get(Employee.class, 2L);
		System.out.println(employee);*/

        session.getTransaction().commit();
        session.close();

    }

}
