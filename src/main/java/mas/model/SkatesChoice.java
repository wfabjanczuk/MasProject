package mas.model;

import mas.entity.Skates;
import mas.entity.SkatesState;

import java.math.BigDecimal;
import java.util.Date;

public class SkatesChoice {
    private Integer id;
    private String model;
    private String size;
    private Date dateBought;
    private BigDecimal skatesBookingPrice;
    private SkatesState skatesState;

    public SkatesChoice(Skates skatesPrototype) {
        id = skatesPrototype.getId();
        model = skatesPrototype.getModel();
        size = skatesPrototype.getSize();
        dateBought = skatesPrototype.getDateBought();
        skatesBookingPrice = skatesPrototype.getSkatesBookingPrice();
        skatesState = skatesPrototype.getSkatesState();
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

    public Date getDateBought() {
        return dateBought;
    }

    public void setDateBought(Date dateBought) {
        this.dateBought = dateBought;
    }

    public BigDecimal getSkatesBookingPrice() {
        return skatesBookingPrice;
    }

    public void setSkatesBookingPrice(BigDecimal skatesBookingPrice) {
        this.skatesBookingPrice = skatesBookingPrice;
    }

    public SkatesState getSkatesState() {
        return skatesState;
    }

    public void setSkatesState(SkatesState skatesState) {
        this.skatesState = skatesState;
    }

    public String toString() {
        return "[ID: " + getId() + "] " + getModel() + ", rozmiar: " + getSize();
    }
}
