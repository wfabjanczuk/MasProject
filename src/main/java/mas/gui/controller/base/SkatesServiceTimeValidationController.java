package mas.gui.controller.base;

import javafx.scene.text.Text;
import mas.model.SkatesServiceTimeValidation;
import mas.service.SkatesServiceService;

import java.util.Date;

abstract public class SkatesServiceTimeValidationController extends ChosenSkatesController {
    protected final SkatesServiceService skatesServiceService;
    protected Date dateStart;
    protected Date dateEnd;

    public Text errorText;

    public SkatesServiceTimeValidationController() {
        skatesServiceService = new SkatesServiceService();
    }

    public boolean validateSkatesServiceTime(Date dateStart, Date dateEnd) {
        SkatesServiceTimeValidation result = skatesServiceService.validateSkatesServiceTime(
                skates.getId(),
                dateStart,
                dateEnd
        );

        switch (result) {
            case DATE_START_IS_NULL:
                errorText.setText("Błąd: data rozpoczęcia nie może być pusta.");
                return false;
            case ZERO_TIME_DIFFERENCE:
                errorText.setText("Błąd: czas trwania przeglądu nie może być równy 0.");
                return false;
            case DATE_START_AFTER_DATE_END:
                errorText.setText("Błąd: data rozpoczęcia nie może wystąpić po dacie zakończenia.");
                return false;
            case OVERLAPPING_SERVICE_EXISTS:
                errorText.setText("Błąd: istnieje już przegląd wybranym okresie.");
                return false;
            case SUCCESS:
                errorText.setText(null);
        }
        return true;
    }
}
