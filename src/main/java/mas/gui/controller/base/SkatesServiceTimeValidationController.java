package mas.gui.controller.base;

import javafx.scene.text.Text;
import mas.entity.SkatesBooking;
import mas.model.SkatesServiceTimeValidation;
import mas.service.SkatesBookingService;
import mas.service.SkatesServiceService;

import java.util.Date;
import java.util.List;

abstract public class SkatesServiceTimeValidationController extends ChosenSkatesController {
    protected final SkatesServiceService skatesServiceService;
    protected final SkatesBookingService skatesBookingService;
    protected Date dateStart;
    protected Date dateEnd;

    public Text errorText;

    public SkatesServiceTimeValidationController() {
        skatesServiceService = new SkatesServiceService();
        skatesBookingService = new SkatesBookingService();
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
            case DATE_END_BEFORE_DATE_START:
                errorText.setText("Błąd: data zakończenia nie może wystąpić przed datą rozpoczęcia.");
                return false;
            case OVERLAPPING_SERVICE_EXISTS:
                errorText.setText("Błąd: istnieje już przegląd wybranym okresie.");
                return false;
            case SUCCESS:
                errorText.setText(null);
        }
        return true;
    }

    public boolean validateSkatesBookings(Date dateStart) {
        List<SkatesBooking> skatesBookings = skatesBookingService.findServicedSkatesBookingsAfter(skates.getId(), dateStart);

        if (skatesBookings.isEmpty()) {
            return true;
        }

        clientGuiState.setFirstSkatesBookings(skatesBookings);
        return false;
    }
}
