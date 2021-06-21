package mas.entity.skatingsession;

import javax.persistence.*;

@Entity
@Table(name = "skating_session_public")
public class SkatingSessionPublic {
    @Id
    private Integer id;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "id")
    @MapsId
    private SkatingSession skatingSession;

    public SkatingSessionPublic() {
    }

    public SkatingSessionPublic(SkatingSession skatingSession) {
        this.skatingSession = skatingSession;

        skatingSession.setSkatingSessionPublic(this);
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
            skatingSession.setSkatingSessionPublic(this);
        }
    }
}