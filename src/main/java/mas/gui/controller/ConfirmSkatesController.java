package mas.gui.controller;

import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import mas.model.SkatesChoice;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ConfirmSkatesController extends Controller {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final String skatesImagePath = "image/skates.jpg";
    private SkatesChoice skatesChoice;

    public ImageView skatesImageView;
    public Text skatesIdText;
    public Text skatesModelText;
    public Text skatesSizeText;
    public Text skatesDateBoughtText;
    public Text skatesBookingPriceText;
    public Text skatesStateText;

    public void initialize() {
        skatesChoice = clientGui.getState().getSkatesChoice();

        skatesIdText.setText(String.valueOf(skatesChoice.getId()));
        skatesModelText.setText(skatesChoice.getModel());
        skatesSizeText.setText(skatesChoice.getSize());
        skatesDateBoughtText.setText(simpleDateFormat.format(skatesChoice.getDateBought()));
        skatesBookingPriceText.setText(skatesChoice.getSkatesBookingPrice().toString() + " zł/sesja");
        skatesStateText.setText(skatesChoice.getSkatesState().toString());
        skatesImageView.setImage(new Image("image/skates/" + skatesChoice.getImage()));
    }

    public void onConfirmSkatesClicked(Event e) throws IOException {
        clientGui.setIntroScene();
    }

    public void onCancelClicked(Event e) throws IOException {
        clientGui.setIntroScene();
    }
}
