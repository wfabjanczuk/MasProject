package mas.repository;

import mas.entity.SkatesService;
import org.hibernate.query.Query;

import java.util.Date;

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
}
