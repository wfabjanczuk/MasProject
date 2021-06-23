package mas.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Skates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 63, nullable = false)
    private String model;

    @Column(length = 15, nullable = false)
    private String size;

    @Column(name = "date_bought", nullable = false)
    private Date dateBought;

    @Column(name = "skates_booking_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal skatesBookingPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "skates_state", length = 63, nullable = false)
    private SkatesState skatesState;

    @OneToMany(mappedBy = "skates")
    private Set<SkatesBooking> skatesBookings = new HashSet<>();

    @OneToMany(mappedBy = "skates")
    private Set<SkatesService> skatesServices = new HashSet<>();

    public Skates() {
    }

    public Skates(String model, String size, Date dateBought, BigDecimal skatesBookingPrice, SkatesState skatesState) {
        this.model = model;
        this.size = size;
        this.dateBought = dateBought;
        this.skatesBookingPrice = skatesBookingPrice;
        this.skatesState = skatesState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Date getDateBought() {
        return dateBought;
    }

    public void setDateBought(Date dateBought) {
        this.dateBought = dateBought;
    }

    public BigDecimal getSkatesBookingPrice() {
        return skatesBookingPrice;
    }

    public void setSkatesBookingPrice(BigDecimal skatesBookingPrice) {
        this.skatesBookingPrice = skatesBookingPrice;
    }

    public SkatesState getSkatesState() {
        return skatesState;
    }

    public void setSkatesState(SkatesState skatesState) {
        this.skatesState = skatesState;
    }

    public Set<SkatesBooking> getSkatesBookings() {
        return skatesBookings;
    }

    public void setSkatesBookings(Set<SkatesBooking> skatesBookings) {
        this.skatesBookings = skatesBookings;
    }

    public Set<SkatesService> getSkatesServices() {
        return skatesServices;
    }

    public void setSkatesServices(Set<SkatesService> skatesServices) {
        this.skatesServices = skatesServices;
    }
}
