package mas.model;

import mas.entity.SkatesBooking;
import mas.entity.SkatingSession;
import mas.entity.person.Person;
import mas.service.DateService;

import java.util.Date;

public class SkatesBookingChoice {
    public Integer id;
    public Date skatingSessionDateStart;
    public String clientName;
    public String clientEmail;

    public SkatesBookingChoice(SkatesBooking skatesBooking) {
        id = skatesBooking.getId();

        SkatingSession skatingSession = skatesBooking.getSkatingSession();
        skatingSessionDateStart = skatingSession.getDateStart();

        Person person = skatesBooking.getClient().getPerson();
        clientName = person.getFirstName() + " " + person.getLastName();
        clientEmail = person.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSkatingSessionDateStart() {
        return skatingSessionDateStart;
    }

    public void setSkatingSessionDateStart(Date skatingSessionDateStart) {
        this.skatingSessionDateStart = skatingSessionDateStart;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    @Override
    public String toString() {
        return "[" + DateService.simpleDateTimeFormat.format(skatingSessionDateStart) + "] " +
                "klient: " + getClientName() + ", email: " + getClientEmail();
    }
}
