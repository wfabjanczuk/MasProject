package mas.entity.person;

import mas.entity.SkatingSession;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(mappedBy = "ticketCollectors")
    private Set<SkatingSession> skatingSessions = new HashSet<>();

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

    public Set<SkatingSession> getSkatingSessions() {
        return skatingSessions;
    }

    public void setSkatingSessions(Set<SkatingSession> skatingSessions) {
        this.skatingSessions = skatingSessions;
    }
}
