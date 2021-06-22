package mas.entity;

import mas.entity.person.Client;
import mas.entity.skatingsession.SkatingSession;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "skating_session_id"}))
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_sold", nullable = false)
    private Date dateSold;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "skating_session_id", nullable = false)
    private SkatingSession skatingSession;

    public Ticket() {
    }

    public Ticket(Date dateSold, Client client, SkatingSession skatingSession) {
        this.dateSold = dateSold;
        this.client = client;
        this.skatingSession = skatingSession;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateSold() {
        return dateSold;
    }

    public void setDateSold(Date dateSold) {
        this.dateSold = dateSold;
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
}
