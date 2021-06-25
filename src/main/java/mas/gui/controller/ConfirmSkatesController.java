package mas.gui.controller;

import javafx.event.Event;

import java.io.IOException;

public class ConfirmSkatesController extends ChosenSkatesController {
    public void initialize() {
        showSkatesChoice();
    }

    public void onConfirmSkatesClicked(Event e) throws IOException {
        clientGui.setTimeScene();
    }

    public void onCancelClicked(Event e) throws IOException {
        clientGui.setIntroScene();
    }
}
