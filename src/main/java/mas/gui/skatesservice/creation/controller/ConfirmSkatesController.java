package mas.gui.skatesservice.creation.controller;

import javafx.event.Event;
import mas.gui.skatesservice.creation.controller.base.ChosenSkatesController;

import java.io.IOException;

public class ConfirmSkatesController extends ChosenSkatesController {
    public void initialize() {
        showSkatesChoice();
    }

    public void onConfirmSkatesClicked(Event e) throws IOException {
        clientGui.setTimeScene();
    }

    public void onCancelClicked(Event e) throws IOException {
        clientGui.clearStateAndSetIntroScene();
    }
}
