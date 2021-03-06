package mas.service;

import mas.entity.SkatesService;
import mas.gui.skatesservice.creation.ClientGuiState;
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

        if (dateStart.equals(dateEnd)) {
            return SkatesServiceTimeValidation.ZERO_TIME_DIFFERENCE;
        }

        if (dateEnd != null && dateStart.after(dateEnd)) {
            return SkatesServiceTimeValidation.DATE_END_BEFORE_DATE_START;
        }

        if (skatesServiceRepository.doesOverlappingServiceExists(skatesId, dateStart, dateEnd)) {
            return SkatesServiceTimeValidation.OVERLAPPING_SERVICE_EXISTS;
        }

        return SkatesServiceTimeValidation.SUCCESS;
    }

    public SkatesService findNewestService(int skatesId, Date dateStart) {
        return skatesServiceRepository.findNewestSkatesServiceAfter(skatesId, dateStart);
    }

    public boolean saveSkatesService(ClientGuiState clientGuiState) {
        mas.entity.SkatesService skatesService = new mas.entity.SkatesService(
                clientGuiState.getSkatesServiceDateStart(),
                clientGuiState.getSkatesServiceDateEnd(),
                clientGuiState.isSkatesServiceSharpening(),
                clientGuiState.isSkatesServiceRepairing(),
                clientGuiState.getSkatesStateAfterService(),
                clientGuiState.getSkates()
        );
        skatesService.setTechnicians(clientGuiState.getTechnicians());

        return skatesServiceRepository.saveSkatesService(skatesService);
    }
}
