package mas.gui.controller;

import javafx.event.Event;
import mas.gui.controller.base.Controller;

import java.io.IOException;

public class SuccessController extends Controller {
    public void onGoBackClicked(Event e) throws IOException {
        clientGui.setIntroScene();
    }
}
