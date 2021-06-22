package mas.entity.skatingsession;

import javax.persistence.*;

@Entity
@Table(name = "skating_session_public")
public class SkatingSessionPublic {
    @Id
    private Integer id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    @MapsId
    private SkatingSession skatingSession;

    @Column(name = "max_participants", nullable = false)
    private Integer maxParticipants;

    public SkatingSessionPublic() {
    }

    public SkatingSessionPublic(SkatingSession skatingSession, Integer maxParticipants) {
        this.skatingSession = skatingSession;
        this.maxParticipants = maxParticipants;
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

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }
}