package mas.entity.person;

import mas.entity.SkatesService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Technician {
    @Id
    private Integer id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    @MapsId
    private Employee employee;

    @ElementCollection
    @CollectionTable(
            name = "technician_skill",
            joinColumns = @JoinColumn(name = "technician_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"technician_id", "skill"})
    )
    @Column(name = "skill", length = 255)
    private Collection<String> skills = new ArrayList<>();

    @ManyToMany(mappedBy = "technicians")
    private Set<SkatesService> skatesServices = new HashSet<>();

    public Technician() {
    }

    public Technician(Employee employee, Collection<String> skills) {
        this.employee = employee;
        this.skills = skills;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Collection<String> getSkills() {
        return skills;
    }

    public void setSkills(Collection<String> skills) {
        this.skills = skills;
    }

    public Set<SkatesService> getSkatesServices() {
        return skatesServices;
    }

    public void setSkatesServices(Set<SkatesService> skatesServices) {
        this.skatesServices = skatesServices;
    }
}
