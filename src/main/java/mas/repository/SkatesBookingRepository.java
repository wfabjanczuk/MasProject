package mas.repository;

import mas.entity.SkatesBooking;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class SkatesBookingRepository extends Repository {
    public List<SkatesBooking> findSkatesBookingsAfter(int skatesId, Date date, int maxResults) {
        String hql = "SELECT sb FROM SkatesBooking sb WHERE sb.skates.id = :skatesId AND " +
                "sb.skatingSession.dateStart >= :date ORDER BY sb.skatingSession.dateStart ASC";

        Query<SkatesBooking> query = session.createQuery(hql, SkatesBooking.class)
                .setParameter("skatesId", skatesId)
                .setParameter("date", date)
                .setMaxResults(maxResults);

        return query.getResultList();
    }
}
