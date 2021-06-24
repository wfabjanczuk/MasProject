package mas.service;

import mas.model.SkatesServiceTimeValidation;

import java.util.Date;

public class SkatesServiceService {
    public SkatesServiceTimeValidation validateSkatesServiceTime(int skatesId, Date dateStart, Date dateEnd) {
        if (dateStart == null) {
            return SkatesServiceTimeValidation.DATE_START_IS_NULL;
        }

        if (dateEnd != null && dateStart.after(dateEnd)) {
            return SkatesServiceTimeValidation.DATE_START_AFTER_DATE_END;
        }

        return SkatesServiceTimeValidation.SUCCESS;
    }
}
