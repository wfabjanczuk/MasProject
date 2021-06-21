package mas.entity;

import javax.persistence.*;

@Entity
@Table(name = "ticket_collector")
public class TicketCollector {
    @Id
    private Integer id;

    @OneToOne(cascade = {CascadeType.PERSIST})
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

        employee.setTicketCollector(this);
    }

    public Integer getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        if (this.employee != employee) {
            this.employee = employee;
            employee.setTicketCollector(this);
        }
    }

    public Boolean getAllowedToSell() {
        return isAllowedToSell;
    }

    public void setAllowedToSell(Boolean allowedToSell) {
        isAllowedToSell = allowedToSell;
    }
}
