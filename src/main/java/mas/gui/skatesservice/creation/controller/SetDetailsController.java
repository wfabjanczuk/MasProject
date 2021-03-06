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

        List<TechnicianChoice> technicianChoices = technicianService.getTechnicianChoices();
        techniciansComboBox.getItems().addAll(technicianChoices);

        int[] checkedTechnicianChoicesIds = clientGuiState.getTechnicians()
                .stream()
                .mapToInt(technician -> technicianChoices.stream()
                        .filter(tc -> tc.getId().equals(technician.getId()))
                        .mapToInt(technicianChoices::indexOf)
                        .findFirst()
                        .orElse(-1)
                )
                .toArray();
        techniciansComboBox.getCheckModel().checkIndices(checkedTechnicianChoicesIds);
    }

    public void onSaveDetailsClicked(Event e) throws IOException {
        dateEnd = DateService.convertToDateViaSqlDate(dateEndDatePicker.getLocalDateTime());

        if (!validateRequiredFields()) {
            return;
        }

        if (!validateSkatesServiceTime(dateStart, dateEnd)) {
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

        if (!skatesServiceService.saveSkatesService(clientGuiState)) {
            clientGui.setErrorScene();
            return;
        }

        clientGuiState.clear();
        clientGui.setSuccessScene();
    }

    private boolean validateRequiredFields() {
        System.out.println(dateEnd);
        System.out.println(stateAfterServiceChoiceBox.getValue());
        if (dateEnd != null && stateAfterServiceChoiceBox.getValue() == null) {
            errorText.setText("B????d: w przypadku podania daty zako??czenia, stan ??y??ew po przegl??dzie jest wymagany.");
            return false;
        }

        if (techniciansComboBox.getCheckModel().getCheckedItems().isEmpty()) {
            errorText.setText("B????d: nale??y wybra?? co najmniej jednego technika.");
            return false;
        }

        return true;
    }

    public void onGoBackClicked(Event e) throws IOException {
        clientGui.setTimeScene();
    }
}
