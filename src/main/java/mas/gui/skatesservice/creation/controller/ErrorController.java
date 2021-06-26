package mas.gui.skatesservice.creation.controller;

import javafx.event.Event;
import mas.gui.skatesservice.creation.controller.base.Controller;

import java.io.IOException;

public class ErrorController extends Controller {
    public void onRetryClicked(Event e) throws IOException {
        clientGui.setDetailsScene();
    }

    public void onGoBackClicked(Event e) throws IOException {
        clientGui.clearStateAndSetIntroScene();
    }
}
