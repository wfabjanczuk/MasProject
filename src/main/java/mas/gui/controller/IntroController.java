package mas.gui.controller;

import javafx.event.Event;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import mas.model.SkatesChoice;
import mas.service.SkatesService;
import util.HibernateUtil;

import java.io.IOException;

public class IntroController extends Controller {
    private static final String skatesNotChosenError = "Nie wybrano łyżew do przeglądu.";

    private final SkatesService skatesService;

    public ChoiceBox<SkatesChoice> skatesChoiceBox;
    public Text errorText;

    public IntroController() {
        skatesService = new SkatesService();
    }

    public void initialize() {
        this.skatesChoiceBox.setOnAction(e -> errorText.setText(null));
        this.skatesChoiceBox.getItems().addAll(skatesService.getSkatesChoicesList());
    }

    public void closeResources() {
        HibernateUtil.closeSessionFactory();
    }

    public void onAddSkatesServiceClicked(Event e) throws IOException {
        if (this.skatesChoiceBox.getValue() == null) {
            errorText.setText(skatesNotChosenError);
            return;
        }
    }
}
