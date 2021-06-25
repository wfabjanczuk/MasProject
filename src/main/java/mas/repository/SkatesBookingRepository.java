package mas.repository;

import mas.entity.SkatesBooking;

import java.util.Date;
import java.util.List;

public class SkatesBookingRepository extends Repository {
    public List<SkatesBooking> findSkatesBookingsAfter(int skatesId, Date date) {
        String hql = "SELECT sb FROM SkatesBooking sb WHERE sb.skates.id = :skatesId AND " +
                "sb.skatingSession.dateStart >= :date AND sb.isCancelled = FALSE " +
                "ORDER BY sb.skatingSession.dateStart ASC";

        return session.createQuery(hql, SkatesBooking.class)
                .setParameter("skatesId", skatesId)
                .setParameter("date", date)
                .getResultList();
    }
}
