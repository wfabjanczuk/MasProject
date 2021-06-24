package mas.gui.controller;

import javafx.event.Event;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import mas.model.SkatesServiceTimeValidation;
import mas.service.DateService;
import mas.service.SkatesServiceService;

import java.io.IOException;
import java.util.Date;

public class SetTimeController extends ChosenSkatesController {
    private final SkatesServiceService skatesServiceService;
    private Date dateStart;
    private Date dateEnd;

    public DatePicker dateStartDatePicker;
    public DatePicker dateEndDatePicker;
    public Text errorText;

    public SetTimeController() {
        skatesServiceService = new SkatesServiceService();
    }

    public void initialize() {
        showSkatesChoice();
        this.dateStartDatePicker.setOnAction(e -> validateTime());
        this.dateEndDatePicker.setOnAction(e -> validateTime());
    }

    private boolean validateTime() {
        dateStart = DateService.convertToDateViaSqlDate(dateStartDatePicker.getValue());
        dateEnd = DateService.convertToDateViaSqlDate(dateEndDatePicker.getValue());

        SkatesServiceTimeValidation result = skatesServiceService.validateSkatesServiceTime(
                skatesChoice.getId(),
                dateStart,
                dateEnd
        );

        switch (result) {
            case DATE_START_IS_NULL:
                errorText.setText("Data rozpoczęcia nie może być pusta!");
                return false;
            case DATE_START_AFTER_DATE_END:
                errorText.setText("Data rozpoczęcia nie może wystąpić po dacie zakończenia!");
                return false;
            case SUCCESS:
                errorText.setText(null);
        }
        return true;
    }

    public void onSaveTimeClicked(Event e) throws IOException {
        if (!validateTime()) {
            return;
        }

        clientGui.getState().setSkatesServiceDateStart(dateStart);
        clientGui.getState().setSkatesServiceDateEnd(dateEnd);
        clientGui.setIntroScene();
    }

    public void onCancelClicked(Event e) throws IOException {
        clientGui.setIntroScene();
    }
}
