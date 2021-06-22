package mas.entity.person;

import javax.persistence.*;

@Entity
@Table(name = "ticket_collector")
public class TicketCollector {
    @Id
    private Integer id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    @MapsId
    private Employee employee;

    @Column(name = "is_allowed_to_sell", nullable = false)
    private Boolean isAllowedToSell;

    public TicketCollector() {
    }

    public TicketCollector(Employee employee, Boolean isAllowedToSell) {
        this.employee = employee;
        this.isAllowedToSell = isAllowedToSell;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Boolean getAllowedToSell() {
        return isAllowedToSell;
    }

    public void setAllowedToSell(Boolean allowedToSell) {
        isAllowedToSell = allowedToSell;
    }
}
