package mas.service;

import mas.model.TechnicianChoice;
import mas.repository.TechnicianRepository;

import java.util.Comparator;
import java.util.List;
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
}
