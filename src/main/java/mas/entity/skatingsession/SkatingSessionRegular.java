package mas.entity.skatingsession;

import javax.persistence.*;

@Entity
@Table(name = "skating_session_regular")
public class SkatingSessionRegular {
    @Id
    private Integer id;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "id")
    @MapsId
    private SkatingSession skatingSession;

    public SkatingSessionRegular() {
    }

    public SkatingSessionRegular(SkatingSession skatingSession) {
        this.skatingSession = skatingSession;

        skatingSession.setSkatingSessionRegular(this);
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
            skatingSession.setSkatingSessionRegular(this);
        }
    }
}
