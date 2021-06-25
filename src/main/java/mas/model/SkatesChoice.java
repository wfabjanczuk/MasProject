package mas.model;

import mas.entity.Skates;

public class SkatesChoice {
    private Integer id;
    private String model;
    private String size;

    public SkatesChoice(Skates skatesPrototype) {
        id = skatesPrototype.getId();
        model = skatesPrototype.getModel();
        size = skatesPrototype.getSize();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String toString() {
        return "[ID: " + getId() + "] " + getModel() + ", rozmiar: " + getSize();
    }
}
