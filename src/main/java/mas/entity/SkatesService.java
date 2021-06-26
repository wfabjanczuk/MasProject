package mas.entity;

import mas.entity.person.Technician;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skates_service")
public class SkatesService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_start", nullable = false)
    private Date dateStart;

    @Column(name = "date_end", nullable = true)
    private Date dateEnd;

    @Column(name = "is_sharpening", nullable = false)
    private Boolean isSharpening;

    @Column(name = "is_repairing", nullable = false)
    private Boolean isRepairing;

    @Enumerated(EnumType.STRING)
    @Column(name = "skates_state_after_service", length = 63, nullable = true)
    private SkatesState skatesStateAfterService;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "skates_id", nullable = false)
    private Skates skates;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "skates_service_technician",
            joinColumns = {@JoinColumn(name = "skates_service_id")},
            inverseJoinColumns = {@JoinColumn(name = "technician_id")}
    )
    private Set<Technician> technicians = new HashSet<>();

    public SkatesService() {
    }

    public SkatesService(Date dateStart, Date dateEnd, Boolean isSharpening, Boolean isRepairing, SkatesState skatesStateAfterService, Skates skates) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.isSharpening = isSharpening;
        this.isRepairing = isRepairing;
        this.skatesStateAfterService = skatesStateAfterService;
        this.skates = skates;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getSharpening() {
        return isSharpening;
    }

    public void setSharpening(Boolean sharpening) {
        isSharpening = sharpening;
    }

    public Boolean getRepairing() {
        return isRepairing;
    }

    public void setRepairing(Boolean repairing) {
        isRepairing = repairing;
    }

    public SkatesState getSkatesStateAfterService() {
        return skatesStateAfterService;
    }

    public void setSkatesStateAfterService(SkatesState skatesStateAfterService) {
        this.skatesStateAfterService = skatesStateAfterService;
    }

    public Skates getSkates() {
        return skates;
    }

    public void setSkates(Skates skates) {
        this.skates = skates;
    }

    public Set<Technician> getTechnicians() {
        return technicians;
    }

    public void setTechnicians(Set<Technician> technicians) {
        this.technicians = technicians;
    }
}
