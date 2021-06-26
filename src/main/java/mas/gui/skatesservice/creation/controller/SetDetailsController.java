package mas.gui.skatesservice.creation.controller;

import javafx.event.Event;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import jfxtras.scene.control.LocalDateTimeTextField;
import mas.entity.SkatesState;
import mas.entity.person.Technician;
import mas.gui.skatesservice.creation.controller.base.SkatesServiceTimeValidationController;
import mas.model.TechnicianChoice;
import mas.service.DateService;
import mas.service.SkatesStateService;
import mas.service.TechnicianService;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SetDetailsController extends SkatesServiceTimeValidationController {
    private final TechnicianService technicianService;

    public Text dateStartText;
    public LocalDateTimeTextField dateEndDatePicker;

    public ChoiceBox<SkatesState> stateAfterServiceChoiceBox;
    public CheckBox sharpeningCheckBox;
    public CheckBox repairingCheckBox;
    public CheckComboBox<TechnicianChoice> techniciansComboBox;

    public SetDetailsController() {
        super();
        technicianService = new TechnicianService();
    }

    public void initialize() {
        showSkatesChoice();

        dateStart = clientGuiState.getSkatesServiceDateStart();
        dateEnd = clientGuiState.getSkatesServiceDateEnd();

        dateEndDatePicker.setLocale(DateService.getPolishLocale());

        dateStartText.setText(DateService.simpleDateTimeFormat.format(dateStart));
        dateEndDatePicker.setLocalDateTime(DateService.convertToLocalDateViaSqlDate(dateEnd));

        stateAfterServiceChoiceBox.getItems().addAll(SkatesStateService.getPossibleStatesAfterService());
        stateAfterServiceChoiceBox.setValue(clientGuiState.getSkatesStateAfterService());
        sharpeningCheckBox.setSelected(clientGuiState.isSkatesServiceSharpening());
        repairingCheckBox.setSelected(clientGuiState.isSkatesServiceSharpening());
        techniciansComboBox.getItems().addAll(technicianService.getTechnicianChoices());
    }

    public boolean updateDatesAndValidateTime() {
        dateEnd = DateService.convertToDateViaSqlDate(dateEndDatePicker.getLocalDateTime());

        return validateSkatesServiceTime(dateStart, dateEnd);
    }


    public void onSaveDetailsClicked(Event e) throws IOException {
        if (!validateRequiredFields()) {
            return;
        }

        if (!updateDatesAndValidateTime()) {
            return;
        }

        List<Integer> technicianIds = techniciansComboBox.getCheckModel()
                .getCheckedItems()
                .stream()
                .map(TechnicianChoice::getId)
                .collect(Collectors.toList());
        Set<Technician> technicians = technicianService.getTechniciansByIds(technicianIds);

        clientGuiState.setSkatesServiceDateEnd(dateEnd);
        clientGuiState.setSkatesStateAfterService(stateAfterServiceChoiceBox.getValue());
        clientGuiState.setSkatesServiceSharpening(sharpeningCheckBox.isSelected());
        clientGuiState.setSkatesServiceRepairing(repairingCheckBox.isSelected());
        clientGuiState.setTechnicians(technicians);

        if (!validateSkatesBookings(dateStart)) {
            clientGui.setSkatesBookingsScene(true);
            return;
        }

        skatesServiceService.saveSkatesService(clientGuiState);
        clientGuiState.clear();

        clientGui.setSuccessScene();
    }

    private boolean validateRequiredFields() {
        if (stateAfterServiceChoiceBox.getValue() == null) {
            errorText.setText("Błąd: stan łyżew po przeglądzie jest wymagany.");
            return false;
        }

        if (techniciansComboBox.getCheckModel().getCheckedItems().isEmpty()) {
            errorText.setText("Błąd: należy wybrać co najmniej jednego technika.");
            return false;
        }

        return true;
    }

    public void onGoBackClicked(Event e) throws IOException {
        clientGui.setTimeScene();
    }
}
