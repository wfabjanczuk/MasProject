package mas.service;

import mas.entity.person.Technician;
import mas.model.TechnicianChoice;
import mas.repository.TechnicianRepository;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TechnicianService {
    private final TechnicianRepository technicianRepository;

    public TechnicianService() {
        technicianRepository = new TechnicianRepository();
    }

    public List<TechnicianChoice> getTechnicianChoices() {
        return technicianRepository.findAll()
                .stream()
                .map(TechnicianChoice::new)
                .sorted(Comparator.comparing(TechnicianChoice::getName))
                .collect(Collectors.toList());
    }

    public Set<Technician> getTechniciansByIds(List<Integer> ids) {
        return new HashSet<>(technicianRepository.findByIds(ids));
    }
}
