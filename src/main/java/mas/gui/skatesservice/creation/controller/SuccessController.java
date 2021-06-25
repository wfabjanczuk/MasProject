package mas.gui.skatesservice.creation.controller;

import javafx.event.Event;
import mas.gui.skatesservice.creation.controller.base.Controller;

import java.io.IOException;

public class SuccessController extends Controller {
    public void onGoBackClicked(Event e) throws IOException {
        clientGui.clearStateAndSetIntroScene();
    }
}
