package mas.gui.skatesservice.creation.controller;

import javafx.event.Event;
import javafx.scene.text.Text;
import mas.entity.SkatesService;
import mas.gui.skatesservice.creation.controller.base.Controller;
import mas.service.DateService;

import java.io.IOException;

public class NewerServiceController extends Controller {
    public Text newestServiceId;
    public Text newestServiceDateStart;
    public Text newestServiceDateEnd;
    public Text newestServiceIsSharpening;
    public Text newestServiceIsRepairing;
    public Text newestServiceStateAfterService;

    public void initialize() {
        SkatesService newestService = clientGuiState.getNewestService();

        newestServiceId.setText(newestService.getId().toString());
        newestServiceDateStart.setText(newestService.getDateStart() == null
                ? ""
                : DateService.simpleDateTimeFormat.format(newestService.getDateStart())
        );
        newestServiceDateEnd.setText(newestService.getDateEnd() == null
                ? ""
                : DateService.simpleDateTimeFormat.format(newestService.getDateEnd())
        );
        newestServiceIsSharpening.setText(newestService.getSharpening() ? "tak" : "nie");
        newestServiceIsRepairing.setText(newestService.getRepairing() ? "tak" : "nie");
        newestServiceStateAfterService.setText(newestService.getSkatesStateAfterService().toString());
    }

    public void onContinueClicked(Event e) throws IOException {
        clientGui.setDetailsScene();
    }

    public void onChangeTimeClicked(Event e) throws IOException {
        clientGui.setTimeScene();
    }
}
