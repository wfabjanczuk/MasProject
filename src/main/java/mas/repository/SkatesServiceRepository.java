package mas.repository;

import mas.entity.SkatesService;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class SkatesServiceRepository extends Repository {
    public boolean doesOverlappingServiceExists(int skatesId, Date dateStart, Date dateEnd) {
        String hql = getHqlForOverlappingService(dateEnd);
        Query<SkatesService> query = getQueryForOverlappingService(hql, skatesId, dateStart, dateEnd);

        return query.getResultStream().findFirst().isPresent();
    }

    private String getHqlForOverlappingService(Date dateEnd) {
        if (dateEnd == null) {
            return "SELECT ss FROM SkatesService ss WHERE ss.skates.id = :skatesId AND " +
                    "ss.dateStart <= :dateStart AND :dateStart < ss.dateEnd";
        }

        return "SELECT ss FROM SkatesService ss WHERE ss.skates.id = :skatesId AND " +
                "NOT (ss.dateStart < :dateStart AND ss.dateEnd <= :dateStart OR " +
                "ss.dateStart >= :dateEnd AND ss.dateEnd > :dateEnd)";
    }

    private Query<SkatesService> getQueryForOverlappingService(String hql, int skatesId, Date dateStart, Date dateEnd) {
        Query<SkatesService> query = session.createQuery(hql, SkatesService.class)
                .setParameter("skatesId", skatesId)
                .setParameter("dateStart", dateStart);

        return (dateEnd != null)
                ? query.setParameter("dateEnd", dateEnd)
                : query;
    }

    public SkatesService findNewestSkatesServiceAfter(int skatesId, Date date) {
        String hql = "SELECT ss FROM SkatesService ss WHERE ss.skates.id = :skatesId AND ss.dateStart >= :date ORDER BY ss.dateStart DESC";

        Query<SkatesService> query = session.createQuery(hql, SkatesService.class)
                .setParameter("skatesId", skatesId)
                .setParameter("date", date)
                .setMaxResults(1);

        List<SkatesService> result = query.getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    public boolean saveSkatesService(SkatesService skatesService) {
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            session.persist(skatesService);
            transaction.commit();

            return true;
        } catch (Exception exception) {
            transaction.rollback();
            System.out.println(exception.getMessage());

            return false;
        }
    }
}
