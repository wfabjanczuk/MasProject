package mas.gui.controller;

import javafx.event.Event;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import jfxtras.scene.control.LocalDateTimeTextField;
import mas.entity.SkatesState;
import mas.gui.controller.base.SkatesServiceTimeValidationController;
import mas.service.DateService;
import mas.service.SkatesStateService;

import java.io.IOException;

public class SetDetailsController extends SkatesServiceTimeValidationController {
    public Text dateStartText;
    public LocalDateTimeTextField dateEndDatePicker;

    public ChoiceBox<SkatesState> stateAfterServiceChoiceBox;
    public CheckBox sharpeningCheckBox;
    public CheckBox repairingCheckBox;

    public void initialize() {
        showSkatesChoice();

        dateStart = clientGuiState.getSkatesServiceDateStart();
        dateEnd = clientGuiState.getSkatesServiceDateEnd();

        this.dateEndDatePicker.setLocale(DateService.getPolishLocale());

        dateStartText.setText(simpleDateTimeFormat.format(dateStart));
        this.dateEndDatePicker.setLocalDateTime(DateService.convertToLocalDateViaSqlDate(dateEnd));

        stateAfterServiceChoiceBox.getItems().addAll(SkatesStateService.getPossibleStatesAfterService());
        stateAfterServiceChoiceBox.setValue(clientGuiState.getSkatesStateAfterService());
        sharpeningCheckBox.setSelected(clientGuiState.isSkatesServiceSharpening());
        repairingCheckBox.setSelected(clientGuiState.isSkatesServiceSharpening());
    }

    public boolean updateDatesAndValidateTime() {
        dateEnd = DateService.convertToDateViaSqlDate(dateEndDatePicker.getLocalDateTime());

        return validateSkatesServiceTime(dateStart, dateEnd);
    }


    public void onSaveDetailsClicked(Event e) throws IOException {
        if (!updateDatesAndValidateTime()) {
            return;
        }

        if (!validateRequiredFields()) {
            return;
        }

        clientGuiState.setSkatesServiceDateEnd(dateEnd);
        clientGuiState.setSkatesStateAfterService(stateAfterServiceChoiceBox.getValue());
        clientGuiState.setSkatesServiceSharpening(sharpeningCheckBox.isSelected());
        clientGuiState.setSkatesServiceRepairing(repairingCheckBox.isSelected());

        skatesServiceService.saveSkatesService(clientGuiState);
        clientGuiState.clear();

        clientGui.setSuccessScene();
    }

    private boolean validateRequiredFields() {
        if (stateAfterServiceChoiceBox.getValue() == null) {
            errorText.setText("Błąd: stan łyżew po przeglądzie jest wymagany.");
            return false;
        }

        return true;
    }

    public void onGoBackClicked(Event e) throws IOException {
        clientGui.setTimeScene();
    }
}
