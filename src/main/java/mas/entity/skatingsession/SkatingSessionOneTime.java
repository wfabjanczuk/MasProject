package mas.entity.skatingsession;

import javax.persistence.*;

@Entity
@Table(name = "skating_session_one_time")
public class SkatingSessionOneTime {
    @Id
    private Integer id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    @MapsId
    private SkatingSession skatingSession;

    public SkatingSessionOneTime() {
    }

    public SkatingSessionOneTime(SkatingSession skatingSession) {
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
}
