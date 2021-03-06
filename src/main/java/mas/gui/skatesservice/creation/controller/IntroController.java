package mas.gui.skatesservice.creation.controller;

import javafx.event.Event;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import mas.entity.Skates;
import mas.gui.skatesservice.creation.controller.base.Controller;
import mas.model.SkatesChoice;
import mas.service.SkatesService;
import util.HibernateUtil;

import java.io.IOException;

public class IntroController extends Controller {
    private static final String skatesNotChosenError = "Błąd: nie wybrano łyżew do przeglądu.";

    private final SkatesService skatesService;

    public ChoiceBox<SkatesChoice> skatesChoiceBox;
    public Text errorText;

    public IntroController() {
        skatesService = new SkatesService();
    }

    public void initialize() {
        skatesChoiceBox.setOnAction(e -> errorText.setText(null));
        skatesChoiceBox.getItems().addAll(skatesService.getSkatesChoicesList());
    }

    public void closeResources() {
        HibernateUtil.closeSessionFactory();
    }

    public void onAddSkatesServiceClicked(Event e) throws IOException {
        SkatesChoice skatesChoice = skatesChoiceBox.getValue();

        if (skatesChoice == null) {
            errorText.setText(skatesNotChosenError);
            return;
        }

        Skates skates = skatesService.findById(skatesChoice.getId());
        clientGuiState.setSkates(skates);
        clientGui.setConfirmSkatesScene();
    }
}
