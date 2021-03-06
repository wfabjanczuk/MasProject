package mas.entity.person;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Employee {
    @Id
    private Integer id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    @MapsId
    private Person person;

    @Column(length = 11, nullable = false, unique = true)
    private String pesel;

    @Column(length = 255, nullable = false)
    private String address;

    @Column(length = 15, nullable = false)
    private String telephone;

    @Column(name = "date_employment", nullable = false)
    private Date dateEmployment;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal salary;

    @OneToOne(mappedBy = "employee")
    private Technician technician;

    @OneToOne(mappedBy = "employee")
    private TicketCollector ticketCollector;

    public Employee() {
    }

    public Employee(Person person, String pesel, String address, String telephone, Date dateEmployment, BigDecimal salary) {
        this.person = person;
        this.pesel = pesel;
        this.address = address;
        this.telephone = telephone;
        this.dateEmployment = dateEmployment;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getDateEmployment() {
        return dateEmployment;
    }

    public void setDateEmployment(Date dateEmployment) {
        this.dateEmployment = dateEmployment;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public TicketCollector getTicketCollector() {
        return ticketCollector;
    }

    public void setTicketCollector(TicketCollector ticketCollector) {
        this.ticketCollector = ticketCollector;
    }
}
