package mas.gui.skatesservice.creation.controller;

import javafx.event.Event;
import javafx.scene.control.CheckBox;
import mas.gui.skatesservice.creation.controller.base.Controller;
import mas.model.SkatesBookingChoice;
import mas.service.SkatesBookingService;
import mas.service.SkatesServiceService;
import org.controlsfx.control.CheckListView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SkatesBookingsController extends Controller {
    private static boolean isLastStep = false;

    private final SkatesBookingService skatesBookingService;
    private final SkatesServiceService skatesServiceService;

    public CheckBox toggleAllCheckBox;
    public CheckListView<SkatesBookingChoice> skatesBookingsListView;

    public SkatesBookingsController() {
        super();
        skatesBookingService = new SkatesBookingService();
        skatesServiceService = new SkatesServiceService();
    }

    public static boolean isIsLastStep() {
        return isLastStep;
    }

    public static void setIsLastStep(boolean isLastStep) {
        SkatesBookingsController.isLastStep = isLastStep;
    }

    public void initialize() {
        skatesBookingsListView.getItems().addAll(clientGuiState.getUpcomingSkatesBooking());

        toggleAllCheckBox.setOnAction(e -> {
            if (toggleAllCheckBox.isSelected()) {
                skatesBookingsListView.getCheckModel().checkAll();
            } else {
                skatesBookingsListView.getCheckModel().clearChecks();
            }
        });
    }

    public void onCancelSelectedClicked(Event e) throws IOException {
        List<Integer> selectedIds = skatesBookingsListView.getCheckModel()
                .getCheckedItems()
                .stream()
                .map(SkatesBookingChoice::getId)
                .collect(Collectors.toList());

        skatesBookingService.cancelBookings(selectedIds);
        continueUseCase();
    }

    public void onSaveAndContinueClicked(Event e) throws IOException {
        continueUseCase();
    }

    private void continueUseCase() throws IOException {
        if (!isLastStep) {
            clientGui.setDetailsScene();
            return;
        }

        if (!skatesServiceService.saveSkatesService(clientGuiState)) {
            clientGui.setErrorScene();
            return;
        }

        clientGuiState.clear();
        clientGui.setSuccessScene();
    }
}
