package mas.entity.person;

import mas.entity.Ticket;
import mas.entity.skatingsession.SkatingSessionPrivate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {
    @Id
    private Integer id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    @MapsId
    private Person person;

    @Column(name = "marketing_consent", nullable = false)
    private Boolean marketingConsent;

    @Column(name = "discount_percent", nullable = false)
    private Integer discountPercent;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "client_skating_session_private",
            joinColumns = {@JoinColumn(name = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "skating_session_private_id")}
    )
    private Set<SkatingSessionPrivate> privateSkatingSessions = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<Ticket> tickets = new HashSet<>();

    public Client() {
    }

    public Client(Person person, Boolean marketingConsent, Integer discountPercent) {
        this.person = person;
        this.marketingConsent = marketingConsent;
        this.discountPercent = discountPercent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Boolean getMarketingConsent() {
        return marketingConsent;
    }

    public void setMarketingConsent(Boolean marketingConsent) {
        this.marketingConsent = marketingConsent;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Set<SkatingSessionPrivate> getPrivateSkatingSessions() {
        return privateSkatingSessions;
    }

    public void setPrivateSkatingSessions(Set<SkatingSessionPrivate> privateSkatingSessions) {
        this.privateSkatingSessions = privateSkatingSessions;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
