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

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    @MapsId
    private SkatingSession skatingSession;

    @ManyToMany(mappedBy = "privateSkatingSessions")
    private Set<Client> clients = new HashSet<>();

    public SkatingSessionPrivate() {
    }

    public SkatingSessionPrivate(SkatingSession skatingSession) {
        this.skatingSession = skatingSession;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SkatingSession getSkatingSession() {
        return skatingSession;
    }

    public void setSkatingSession(SkatingSession skatingSession) {
        this.skatingSession = skatingSession;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}