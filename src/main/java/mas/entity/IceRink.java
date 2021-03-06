package mas.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ice_rink")
public class IceRink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String dimensions;

    @Column(nullable = false)
    private Integer area;

    @OneToMany(mappedBy = "iceRink")
    private Set<SkatingSession> skatingSessions = new HashSet<>();

    public IceRink() {
    }

    public IceRink(String name, String dimensions, Integer area) {
        this.name = name;
        this.dimensions = dimensions;
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Set<SkatingSession> getSkatingSessions() {
        return skatingSessions;
    }

    public void setSkatingSessions(Set<SkatingSession> skatingSessions) {
        this.skatingSessions = skatingSessions;
    }
}
