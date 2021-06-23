package mas.entity;

import mas.entity.person.Client;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "skates_booking")
public class SkatesBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_booking", nullable = false)
    private Date dateBooking;

    @Column(name = "is_cancelled", nullable = false)
    private Boolean isCancelled;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "skating_session_id", nullable = false)
    private SkatingSession skatingSession;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "skates_id", nullable = false)
    private Skates skates;

    public SkatesBooking() {
    }

    public SkatesBooking(Date dateBooking, Boolean isCancelled, Client client, SkatingSession skatingSession, Skates skates) {
        this.dateBooking = dateBooking;
        this.isCancelled = isCancelled;
        this.client = client;
        this.skatingSession = skatingSession;
        this.skates = skates;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(Date dateBooking) {
        this.dateBooking = dateBooking;
    }

    public Boolean getCancelled() {
        return isCancelled;
    }

    public void setCancelled(Boolean cancelled) {
        isCancelled = cancelled;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public SkatingSession getSkatingSession() {
        return skatingSession;
    }

    public void setSkatingSession(SkatingSession skatingSession) {
        this.skatingSession = skatingSession;
    }

    public Skates getSkates() {
        return skates;
    }

    public void setSkates(Skates skates) {
        this.skates = skates;
    }
}
