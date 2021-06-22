package mas.entity.skatingsession;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "skating_session_regular")
public class SkatingSessionRegular {
    @Id
    private Integer id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    @MapsId
    private SkatingSession skatingSession;

    @ElementCollection
    @CollectionTable(name = "skating_session_regular_days", joinColumns = @JoinColumn(name = "skating_session_regular_id"))
    @Column(name = "day_of_week")
    private Collection<Integer> daysOfWeek = new ArrayList<>();

    public SkatingSessionRegular() {
    }

    public SkatingSessionRegular(SkatingSession skatingSession, Collection<Integer> daysOfWeek) {
        this.skatingSession = skatingSession;
        this.daysOfWeek = daysOfWeek;
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

    public Collection<Integer> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(Collection<Integer> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
}
