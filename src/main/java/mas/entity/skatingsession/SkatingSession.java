package mas.entity.skatingsession;

import mas.entity.IceRink;
import mas.entity.Ticket;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "ice_rink_id")
    private IceRink iceRink;

    @OneToMany(mappedBy = "skatingSession", cascade = {CascadeType.PERSIST})
    private Set<Ticket> tickets = new HashSet<>();

    @OneToOne(mappedBy = "skatingSession", cascade = {CascadeType.PERSIST})
    private SkatingSessionOneTime skatingSessionOneTime;

    @OneToOne(mappedBy = "skatingSession", cascade = {CascadeType.PERSIST})
    private SkatingSessionRegular skatingSessionRegular;

    @OneToOne(mappedBy = "skatingSession", cascade = {CascadeType.PERSIST})
    private SkatingSessionPrivate skatingSessionPrivate;

    @OneToOne(mappedBy = "skatingSession", cascade = {CascadeType.PERSIST})
    private SkatingSessionPublic skatingSessionPublic;

    public SkatingSession() {
    }

    public SkatingSession(Date dateStart, Date dateEnd, BigDecimal ticketPrice, String description, IceRink iceRink) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.ticketPrice = ticketPrice;
        this.description = description;
        this.iceRink = iceRink;

        iceRink.addSkatingSession(this);
    }

    public Integer getId() {
        return id;
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

    public IceRink getIceRink() {
        return iceRink;
    }

    public void setIceRink(IceRink iceRink) {
        if (this.iceRink != iceRink) {
            this.iceRink = iceRink;
            iceRink.addSkatingSession(this);
        }
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        if (!tickets.contains(ticket)) {
            this.tickets.add(ticket);
            ticket.setSkatingSession(this);
        }
    }

    public SkatingSessionOneTime getSkatingSessionOneTime() {
        return skatingSessionOneTime;
    }

    public void setSkatingSessionOneTime(SkatingSessionOneTime skatingSessionOneTime) {
        if (this.skatingSessionOneTime != skatingSessionOneTime) {
            this.skatingSessionOneTime = skatingSessionOneTime;
            skatingSessionOneTime.setSkatingSession(this);
        }
    }

    public SkatingSessionRegular getSkatingSessionRegular() {
        return skatingSessionRegular;
    }

    public void setSkatingSessionRegular(SkatingSessionRegular skatingSessionRegular) {
        if (this.skatingSessionRegular != skatingSessionRegular) {
            this.skatingSessionRegular = skatingSessionRegular;
            skatingSessionRegular.setSkatingSession(this);
        }
    }

    public SkatingSessionPrivate getSkatingSessionPrivate() {
        return skatingSessionPrivate;
    }

    public void setSkatingSessionPrivate(SkatingSessionPrivate skatingSessionPrivate) {
        if (this.skatingSessionPrivate != skatingSessionPrivate) {
            this.skatingSessionPrivate = skatingSessionPrivate;
            skatingSessionPrivate.setSkatingSession(this);
        }
    }

    public SkatingSessionPublic getSkatingSessionPublic() {
        return skatingSessionPublic;
    }

    public void setSkatingSessionPublic(SkatingSessionPublic skatingSessionPublic) {
        if (this.skatingSessionPublic != skatingSessionPublic) {
            this.skatingSessionPublic = skatingSessionPublic;
            skatingSessionPublic.setSkatingSession(this);
        }
    }
}
