package mas.service;

import mas.entity.SkatesBooking;
import mas.repository.SkatesBookingRepository;

import java.util.Date;
import java.util.List;

public class SkatesBookingService {
    private final SkatesBookingRepository skatesBookingRepository;

    public SkatesBookingService() {
        skatesBookingRepository = new SkatesBookingRepository();
    }

    public List<SkatesBooking> findServicedSkatesBookingsAfter(int skatesId, Date date) {
        return skatesBookingRepository.findSkatesBookingsAfter(skatesId, date, 10);
    }
}
