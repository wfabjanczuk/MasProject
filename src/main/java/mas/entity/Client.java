package mas.entity;

import javax.persistence.*;

@Entity
public class Client {
    @Id
    private Integer id;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "id")
    @MapsId
    private Person person;

    @Column(name = "marketing_consent", nullable = false)
    private Boolean marketingConsent;

    @Column(name = "discount_percent", nullable = false)
    private Integer discountPercent;

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
}
