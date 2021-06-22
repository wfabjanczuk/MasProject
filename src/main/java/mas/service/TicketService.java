package mas.service;

import mas.entity.Ticket;
import mas.entity.person.Client;
import mas.entity.SkatingSession;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TicketService {
    public static BigDecimal getFinalPrice(Ticket ticket) {
        Client client = ticket.getClient();
        SkatingSession skatingSession = ticket.getSkatingSession();

        if (client == null || client.getDiscountPercent() == null
                || skatingSession == null || skatingSession.getTicketPrice() == null
        ) {
            throw new IllegalStateException();
        }

        return skatingSession.getTicketPrice()
                .multiply(BigDecimal.valueOf(client.getDiscountPercent()))
                .divide(BigDecimal.valueOf(100), RoundingMode.UP);
    }
}
