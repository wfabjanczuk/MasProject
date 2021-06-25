package mas.gui.controller;

import javafx.event.Event;

import java.io.IOException;

public class SuccessController extends Controller {
    public void onGoBackClicked(Event e) throws IOException {
        clientGui.setIntroScene();
    }
}
