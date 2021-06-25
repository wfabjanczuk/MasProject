package mas.gui.controller;

import javafx.event.Event;
import javafx.scene.control.DatePicker;
import mas.entity.SkatesService;
import mas.gui.controller.base.SkatesServiceTimeValidationController;
import mas.service.DateService;

import java.io.IOException;

public class SetTimeController extends SkatesServiceTimeValidationController {
    public DatePicker dateStartDatePicker;
    public DatePicker dateEndDatePicker;

    public void initialize() {
        showSkatesChoice();

        this.dateStartDatePicker.setOnAction(e -> updateDatesAndValidateTime());
        this.dateEndDatePicker.setOnAction(e -> updateDatesAndValidateTime());

        dateStart = clientGuiState.getSkatesServiceDateStart();
        dateEnd = clientGuiState.getSkatesServiceDateEnd();

        this.dateStartDatePicker.setValue(DateService.convertToLocalDateViaSqlDate(dateStart));
        this.dateEndDatePicker.setValue(DateService.convertToLocalDateViaSqlDate(dateEnd));
    }

    public boolean updateDatesAndValidateTime() {
        dateStart = DateService.convertToDateViaSqlDate(dateStartDatePicker.getValue());
        dateEnd = DateService.convertToDateViaSqlDate(dateEndDatePicker.getValue());

        return validateSkatesServiceTime(dateStart, dateEnd);
    }

    public void onSaveTimeClicked(Event e) throws IOException {
        if (!updateDatesAndValidateTime()) {
            return;
        }

        clientGuiState.setSkatesServiceDateStart(dateStart);
        clientGuiState.setSkatesServiceDateEnd(dateEnd);

        SkatesService newerSkatesService = skatesServiceService.findNewerService(dateStart);

        if (newerSkatesService != null) {
            errorText.setText("Istnieje nowszy przegląd!");
            return;
        }

        clientGui.setDetailsScene();
    }

    public void onCancelClicked(Event e) throws IOException {
        clientGui.setIntroScene();
    }
}
