package mas.gui.controller;

import javafx.event.Event;
import javafx.scene.control.DatePicker;
import jfxtras.scene.control.LocalDateTimePicker;
import jfxtras.scene.control.LocalDateTimeTextField;
import mas.entity.SkatesService;
import mas.gui.controller.base.SkatesServiceTimeValidationController;
import mas.service.DateService;

import java.io.IOException;

public class SetTimeController extends SkatesServiceTimeValidationController {
    public LocalDateTimeTextField dateStartDatePicker;
    public LocalDateTimeTextField dateEndDatePicker;

    public void initialize() {
        showSkatesChoice();

        this.dateStartDatePicker.setLocale(DateService.getPolishLocale());
        this.dateEndDatePicker.setLocale(DateService.getPolishLocale());

        dateStart = clientGuiState.getSkatesServiceDateStart();
        dateEnd = clientGuiState.getSkatesServiceDateEnd();

        this.dateStartDatePicker.setLocalDateTime(DateService.convertToLocalDateViaSqlDate(dateStart));
        this.dateEndDatePicker.setLocalDateTime(DateService.convertToLocalDateViaSqlDate(dateEnd));
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
        }

        clientGui.setDetailsScene();
    }

    public void onCancelClicked(Event e) throws IOException {
        clientGui.setIntroScene();
    }
}
