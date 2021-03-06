package mas.gui.skatesservice.creation.controller;

import javafx.event.Event;
import jfxtras.scene.control.LocalDateTimeTextField;
import mas.entity.SkatesService;
import mas.gui.skatesservice.creation.controller.base.SkatesServiceTimeValidationController;
import mas.service.DateService;

import java.io.IOException;

public class SetTimeController extends SkatesServiceTimeValidationController {
    public LocalDateTimeTextField dateStartDatePicker;
    public LocalDateTimeTextField dateEndDatePicker;

    public void initialize() {
        showSkatesChoice();

        dateStartDatePicker.setLocale(DateService.getPolishLocale());
        dateEndDatePicker.setLocale(DateService.getPolishLocale());

        dateStart = clientGuiState.getSkatesServiceDateStart();
        dateEnd = clientGuiState.getSkatesServiceDateEnd();

        dateStartDatePicker.setLocalDateTime(DateService.convertToLocalDateViaSqlDate(dateStart));
        dateEndDatePicker.setLocalDateTime(DateService.convertToLocalDateViaSqlDate(dateEnd));
    }

    public boolean updateDatesAndValidateTime() {
        dateStart = DateService.convertToDateViaSqlDate(dateStartDatePicker.getLocalDateTime());
        dateEnd = DateService.convertToDateViaSqlDate(dateEndDatePicker.getLocalDateTime());

        return validateSkatesServiceTime(dateStart, dateEnd);
    }

    public void onSaveTimeClicked(Event e) throws IOException {
        if (!updateDatesAndValidateTime()) {
            return;
        }

        clientGuiState.setSkatesServiceDateStart(dateStart);
        clientGuiState.setSkatesServiceDateEnd(dateEnd);

        SkatesService newestService = skatesServiceService.findNewestService(skates.getId(), dateStart);

        if (newestService != null) {
            clientGuiState.setNewestService(newestService);
            clientGui.setNewerServiceScene();
            return;
        } else if (!validateSkatesBookings(dateStart)) {
            clientGui.setSkatesBookingsScene(false);
            return;
        }

        clientGui.setDetailsScene();
    }

    public void onCancelClicked(Event e) throws IOException {
        clientGui.clearStateAndSetIntroScene();
    }
}
