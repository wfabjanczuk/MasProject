package mas.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "skating_session")
public class SkatingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_start", nullable = false)
    private Date dateStart;

    @Column(name = "date_end", nullable = false)
    private Date dateEnd;

    @Column(name = "ticket_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal ticketPrice;

    @Column(length = 255, nullable = false)
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "ice_rink_id")
    private IceRink iceRink;

    public SkatingSession() {
    }

    public SkatingSession(Date dateStart, Date dateEnd, BigDecimal ticketPrice, String description, IceRink iceRink) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.ticketPrice = ticketPrice;
        this.description = description;
        this.iceRink = iceRink;

        iceRink.addSkatingSession(this);
    }

    public Integer getId() {
        return id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IceRink getIceRink() {
        return iceRink;
    }

    public void setIceRink(IceRink iceRink) {
        if (this.iceRink != iceRink) {
            this.iceRink = iceRink;
            iceRink.addSkatingSession(this);
        }
    }
}
