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

    @Column(name = "date_birth", nullable = false)
    private Date dateBirth;

    @OneToOne(mappedBy = "person", cascade = {CascadeType.PERSIST})
    private Client client;

    @OneToOne(mappedBy = "person", cascade = {CascadeType.PERSIST})
    private Employee employee;

    public Person() {
    }

    public Person(String email, String firstname, String lastname, Date dateBirth) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateBirth = dateBirth;
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

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Integer getAge() {
        if (dateBirth == null) {
            return null;
        }

        return DateUtil.getYearDifference(dateBirth, new Date());
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (this.client != client) {
            this.client = client;
            client.setPerson(this);
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        if (this.employee != employee) {
            this.employee = employee;
            employee.setPerson(this);
        }
    }
}
