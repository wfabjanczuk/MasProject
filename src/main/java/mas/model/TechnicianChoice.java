package mas.model;

import mas.entity.person.Person;
import mas.entity.person.Technician;

public class TechnicianChoice {
    public Integer id;
    public String name;

    public TechnicianChoice(Technician technician) {
        id = technician.getId();

        Person person = technician.getEmployee().getPerson();
        name = person.getFirstName() + " " + person.getLastName();
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

    public String toString() {
        return getName();
    }
}
