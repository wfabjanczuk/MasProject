package mas.entity.skatingsession;

import javax.persistence.*;

@Entity
@Table(name = "skating_session_one_time")
public class SkatingSessionOneTime {
    @Id
    private Integer id;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "id")
    @MapsId
    private SkatingSession skatingSession;

    public SkatingSessionOneTime() {
    }

    public SkatingSessionOneTime(SkatingSession skatingSession) {
        this.skatingSession = skatingSession;

        skatingSession.setSkatingSessionOneTime(this);
    }

    public Integer getId() {
        return id;
    }

    public SkatingSession getSkatingSession() {
        return skatingSession;
    }

    public void setSkatingSession(SkatingSession skatingSession) {
        if (this.skatingSession != skatingSession) {
            this.skatingSession = skatingSession;
            skatingSession.setSkatingSessionOneTime(this);
        }
    }
}
