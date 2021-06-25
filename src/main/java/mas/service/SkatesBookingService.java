package mas.service;

import mas.model.SkatesBookingChoice;
import mas.repository.SkatesBookingRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SkatesBookingService {
    private final SkatesBookingRepository skatesBookingRepository;

    public SkatesBookingService() {
        skatesBookingRepository = new SkatesBookingRepository();
    }

    public List<SkatesBookingChoice> findServicedSkatesBookingsAfter(int skatesId, Date date) {
        return skatesBookingRepository
                .findSkatesBookingsAfter(skatesId, date)
                .stream()
                .map(SkatesBookingChoice::new)
                .collect(Collectors.toList());
    }

    public boolean cancelBookings(List<Integer> ids) {
        return skatesBookingRepository.cancelSelectedBookings(ids);
    }
}
