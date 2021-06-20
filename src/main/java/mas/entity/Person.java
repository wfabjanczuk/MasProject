package mas.entity;

import util.DateUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 320, nullable = false, unique = true)
    private String email;

    @Column(length = 64, nullable = false)
    private String firstname;

    @Column(length = 64, nullable = false)
    private String lastname;

    @Column(nullable = false)
    private Date birthdate;

    @OneToOne(mappedBy = "person")
    private Client client;

    @OneToOne(mappedBy = "person")
    private Employee employee;

    public Person() {
    }

    public Person(String email, String firstname, String lastname, Date birthdate) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getAge() {
        if (birthdate == null) {
            return null;
        }

        return DateUtil.getYearDifference(birthdate, new Date());
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
        client.setPerson(this);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        employee.setPerson(this);
    }
}
