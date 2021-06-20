package mas.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Employee {
    @Id
    private Integer personId;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "person_id")
    @MapsId
    private Person person;

    @Column(length = 11, nullable = false, unique = true)
    private String pesel;

    @Column(length = 255, nullable = false)
    private String address;

    @Column(length = 15, nullable = false)
    private String telephone;

    @Column(name = "employment_date", nullable = false)
    private Date employmentDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal salary;

    public Employee() {
    }

    public Employee(Person person, String pesel, String address, String telephone, Date employmentDate, BigDecimal salary) {
        this.person = person;
        this.pesel = pesel;
        this.address = address;
        this.telephone = telephone;
        this.employmentDate = employmentDate;
        this.salary = salary;
    }

    public Integer getPersonId() {
        return personId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
