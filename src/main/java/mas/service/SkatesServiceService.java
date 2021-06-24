package mas.service;

import mas.model.SkatesServiceTimeValidation;
import mas.repository.SkatesServiceRepository;

import java.util.Date;

public class SkatesServiceService {
    private final SkatesServiceRepository skatesServiceRepository;

    public SkatesServiceService() {
        this.skatesServiceRepository = new SkatesServiceRepository();
    }

    public SkatesServiceTimeValidation validateSkatesServiceTime(int skatesId, Date dateStart, Date dateEnd) {
        if (dateStart == null) {
            return SkatesServiceTimeValidation.DATE_START_IS_NULL;
        }

        if (dateEnd != null && dateStart.after(dateEnd)) {
            return SkatesServiceTimeValidation.DATE_START_AFTER_DATE_END;
        }

        if (skatesServiceRepository.doesOverlappingServiceExists(skatesId, dateStart, dateEnd)) {
            return SkatesServiceTimeValidation.OVERLAPPING_SERVICE_EXISTS;
        }

        return SkatesServiceTimeValidation.SUCCESS;
    }
}
