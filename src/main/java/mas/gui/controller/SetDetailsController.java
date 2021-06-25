package mas.gui.controller;

import javafx.event.Event;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import mas.entity.SkatesState;
import mas.service.DateService;
import mas.service.SkatesStateService;

import java.io.IOException;

public class SetDetailsController extends SkatesServiceTimeValidationController {
    public Text dateStartText;
    public DatePicker dateEndDatePicker;

    public ChoiceBox<SkatesState> stateAfterServiceChoiceBox;
    public CheckBox sharpeningCheckBox;
    public CheckBox repairingCheckBox;

    public void initialize() {
        showSkatesChoice();

        dateStart = clientGuiState.getSkatesServiceDateStart();
        dateEnd = clientGuiState.getSkatesServiceDateEnd();

        this.dateEndDatePicker.setOnAction(e -> updateDatesAndValidateTime());

        dateStartText.setText(simpleDateFormat.format(dateStart));
        this.dateEndDatePicker.setValue(DateService.convertToLocalDateViaSqlDate(dateEnd));

        stateAfterServiceChoiceBox.getItems().addAll(SkatesStateService.getPossibleStatesAfterService());
        stateAfterServiceChoiceBox.setValue(clientGuiState.getSkatesStateAfterService());
        sharpeningCheckBox.setSelected(clientGuiState.isSkatesServiceIsSharpening());
        repairingCheckBox.setSelected(clientGuiState.isSkatesServiceIsSharpening());
    }

    public boolean updateDatesAndValidateTime() {
        dateEnd = DateService.convertToDateViaSqlDate(dateEndDatePicker.getValue());

        return validateSkatesServiceTime(dateStart, dateEnd);
    }


    public void onSaveDetailsClicked(Event e) throws IOException {
        if (!updateDatesAndValidateTime()) {
            return;
        }

        clientGuiState.setSkatesServiceDateEnd(dateEnd);
        clientGuiState.setSkatesStateAfterService(stateAfterServiceChoiceBox.getValue());
        clientGuiState.setSkatesServiceIsSharpening(sharpeningCheckBox.isSelected());
        clientGuiState.setSkatesServiceIsRepairing(repairingCheckBox.isSelected());

        skatesServiceService.saveSkatesService(clientGuiState);
    }

    public void onGoBackClicked(Event e) throws IOException {
        clientGui.setTimeScene();
    }
}
