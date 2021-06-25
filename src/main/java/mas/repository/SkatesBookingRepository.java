package mas.repository;

import mas.entity.SkatesBooking;
import org.hibernate.Transaction;

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

    public boolean cancelSelectedBookings(List<Integer> ids) {
        String hql = "UPDATE SkatesBooking sb SET sb.isCancelled = 1 WHERE sb.id IN (:ids)";
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            int affectedRows = session.createQuery(hql).setParameterList("ids", ids).executeUpdate();
            transaction.commit();

            return affectedRows > 0;
        } catch (Exception exception) {
            transaction.rollback();
            System.out.println(exception.getMessage());

            return false;
        }
    }
}
