package mas.repository;

import mas.entity.Skates;

import java.util.List;
import java.util.stream.Collectors;

public class SkatesRepository extends Repository {
    public List<Skates> findAll() {
        return session.createQuery("SELECT s FROM Skates s", Skates.class)
                .getResultStream()
                .collect(Collectors.toList());
    }

    public Skates findById(int skatesId) {
        return session.get(Skates.class, skatesId);
    }
}
