package mas.entity.skatingsession;

import mas.entity.person.Client;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skating_session_private")
public class SkatingSessionPrivate {
    @Id
    private Integer id;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "id")
    @MapsId
    private SkatingSession skatingSession;

    @ManyToMany(mappedBy = "privateSkatingSessions", cascade = {CascadeType.PERSIST})
    private Set<Client> clients = new HashSet<>();

    public SkatingSessionPrivate() {
    }

    public SkatingSessionPrivate(SkatingSession skatingSession) {
        this.skatingSession = skatingSession;

        skatingSession.setSkatingSessionPrivate(this);
    }

    public Integer getId() {
        return id;
    }

    public SkatingSession getSkatingSession() {
        return skatingSession;
    }

    public void setSkatingSession(SkatingSession skatingSession) {
        if (this.skatingSession != skatingSession) {
            this.skatingSession = skatingSession;
            skatingSession.setSkatingSessionPrivate(this);
        }
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        if (!this.clients.contains(client)) {
            this.clients.add(client);
            client.addPrivateSkatingSession(this);
        }
    }
}