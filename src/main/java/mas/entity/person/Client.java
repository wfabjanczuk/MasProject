package mas.entity.person;

import mas.entity.SkatesBooking;
import mas.entity.SkatingSession;
import mas.entity.Ticket;

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
            name = "client_private_skating_session",
            joinColumns = {@JoinColumn(name = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "private_skating_session_id")}
    )
    private Set<SkatingSession> privateSkatingSessions = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<Ticket> tickets = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<SkatesBooking> skatesBookings = new HashSet<>();

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

    public Set<SkatingSession> getPrivateSkatingSessions() {
        return privateSkatingSessions;
    }

    public void setPrivateSkatingSessions(Set<SkatingSession> privateSkatingSessions) {
        this.privateSkatingSessions = privateSkatingSessions;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<SkatesBooking> getSkatesBookings() {
        return skatesBookings;
    }

    public void setSkatesBookings(Set<SkatesBooking> skatesBookings) {
        this.skatesBookings = skatesBookings;
    }
}
