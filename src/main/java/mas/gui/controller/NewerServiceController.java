package mas.gui.controller;

import javafx.event.Event;
import javafx.scene.text.Text;
import mas.gui.controller.base.Controller;

import java.io.IOException;

public class NewerServiceController extends Controller {
    public Text newestServiceId;
    public Text newestServiceDateStart;
    public Text newestServiceDateEnd;
    public Text newestServiceIsSharpening;
    public Text newestServiceIsRepairing;
    public Text newestServiceStateAfterService;

    public void initialize() {
        newestServiceId.setText(clientGuiState.getNewestService().getId().toString());
        newestServiceDateStart.setText(clientGuiState.getNewestService().getDateStart() == null
                ? ""
                : simpleDateTimeFormat.format(clientGuiState.getNewestService().getDateStart())
        );
        newestServiceDateEnd.setText(clientGuiState.getNewestService().getDateEnd() == null
                ? ""
                : simpleDateTimeFormat.format(clientGuiState.getNewestService().getDateEnd())
        );
        newestServiceIsSharpening.setText(clientGuiState.getNewestService().getSharpening() ? "tak" : "nie");
        newestServiceIsRepairing.setText(clientGuiState.getNewestService().getRepairing() ? "tak" : "nie");
        newestServiceStateAfterService.setText(clientGuiState.getNewestService().getSkatesStateAfterService().toString());
    }

    public void onContinueClicked(Event e) throws IOException {
        clientGui.setDetailsScene();
    }

    public void onChangeTimeClicked(Event e) throws IOException {
        clientGui.setTimeScene();
    }
}
