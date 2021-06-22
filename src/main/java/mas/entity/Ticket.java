package mas.entity;

import mas.entity.person.Client;
import mas.entity.skatingsession.SkatingSession;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_sold", nullable = false)
    private Date dateSold;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "skating_session_id")
    private SkatingSession skatingSession;

    public Ticket() {
    }

    public Integer getId() {
        return id;
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
        if (this.client != client) {
            this.client = client;
            client.addTicket(this);
        }
    }

    public SkatingSession getSkatingSession() {
        return skatingSession;
    }

    public void setSkatingSession(SkatingSession skatingSession) {
        if (this.skatingSession != skatingSession) {
            this.skatingSession = skatingSession;
            skatingSession.addTicket(this);
        }
    }
}
