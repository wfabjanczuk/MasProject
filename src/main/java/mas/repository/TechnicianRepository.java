package mas.repository;

import mas.entity.person.Technician;

import java.util.List;
import java.util.stream.Collectors;

public class TechnicianRepository extends Repository {
    public List<Technician> findAll() {
        return session.createQuery("SELECT t FROM Technician t", Technician.class)
                .getResultStream()
                .collect(Collectors.toList());
    }
}
