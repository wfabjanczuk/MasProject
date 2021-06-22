package mas.entity;

import mas.entity.person.Client;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "skating_session")
public class SkatingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_start", nullable = false)
    private Date dateStart;

    @Column(name = "date_end", nullable = false)
    private Date dateEnd;

    @Column(name = "ticket_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal ticketPrice;

    @Column(length = 255, nullable = false)
    private String description;

    @Column(name = "is_private", nullable = false)
    private Boolean isPrivate;

    @Column(name = "is_regular", nullable = false)
    private Boolean isRegular;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ice_rink_id", nullable = false)
    private IceRink iceRink;

    @OneToMany(mappedBy = "skatingSession")
    private Set<Ticket> tickets = new HashSet<>();

    @ManyToMany(mappedBy = "privateSkatingSessions")
    private Set<Client> clients = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "skating_session_regular_days",
            joinColumns = @JoinColumn(name = "regular_skating_session_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"regular_skating_session_id", "day_of_week"})
    )
    @Column(name = "day_of_week")
    private Collection<Integer> daysOfWeek = new ArrayList<>();

    public SkatingSession() {
    }

    public SkatingSession(Date dateStart, Date dateEnd, BigDecimal ticketPrice, String description, Boolean isPrivate, Boolean isRegular, IceRink iceRink) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.ticketPrice = ticketPrice;
        this.description = description;
        this.isPrivate = isPrivate;
        this.isRegular = isRegular;
        this.iceRink = iceRink;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Boolean getRegular() {
        return isRegular;
    }

    public void setRegular(Boolean isRegular) {
        this.isRegular = isRegular;
    }

    public IceRink getIceRink() {
        return iceRink;
    }

    public void setIceRink(IceRink iceRink) {
        this.iceRink = iceRink;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Collection<Integer> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(Collection<Integer> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
}
