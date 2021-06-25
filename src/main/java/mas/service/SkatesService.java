package mas.service;

import mas.entity.Skates;
import mas.model.SkatesChoice;
import mas.repository.SkatesRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SkatesService {
    private final SkatesRepository skatesRepository;

    public SkatesService() {
        skatesRepository = new SkatesRepository();
    }

    public List<SkatesChoice> getSkatesChoicesList() {
        List<Skates> skatesSet = skatesRepository.findAll();

        return skatesSet.stream()
                .map(SkatesChoice::new)
                .sorted(Comparator.comparingInt(SkatesChoice::getId))
                .collect(Collectors.toList());
    }

    public Skates findById(int skatesId) {
        return skatesRepository.findById(skatesId);
    }
}
