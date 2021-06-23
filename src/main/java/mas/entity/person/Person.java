package mas.entity.person;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 320, nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", length = 64, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 64, nullable = false)
    private String lastName;

    @Column(name = "date_birth", nullable = false)
    private Date dateBirth;

    @OneToOne(mappedBy = "person")
    private Client client;

    @OneToOne(mappedBy = "person")
    private Employee employee;

    public Person() {
    }

    public Person(String email, String firstName, String lastName, Date dateBirth) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
