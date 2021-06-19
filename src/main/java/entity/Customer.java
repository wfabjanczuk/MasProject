package entity;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "passport_id", unique = true)
    private Passport passport;

    public Customer() {
    }

    public Customer(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}
