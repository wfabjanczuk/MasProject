package mas.gui.skatesservice.creation.controller.base;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import mas.entity.Skates;
import mas.service.DateService;

abstract public class ChosenSkatesController extends Controller {
    protected Skates skates;

    public ImageView skatesImageView;
    public Text skatesIdText;
    public Text skatesModelText;
    public Text skatesSizeText;
    public Text skatesDateBoughtText;
    public Text skatesBookingPriceText;
    public Text skatesStateText;

    public void showSkatesChoice() {
        skates = clientGuiState.getSkates();

        skatesIdText.setText(String.valueOf(skates.getId()));
        skatesModelText.setText(skates.getModel());
        skatesSizeText.setText(skates.getSize());
        skatesDateBoughtText.setText(DateService.simpleDateFormat.format(skates.getDateBought()));
        skatesBookingPriceText.setText(skates.getSkatesBookingPrice().toString() + " zł/sesja");
        skatesStateText.setText(skates.getSkatesState().toString());
        skatesImageView.setImage(new Image("image/skates/" + skates.getImage()));
    }
}
