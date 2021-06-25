package mas.gui;

import mas.entity.Skates;
import mas.entity.SkatesState;
import mas.model.SkatesChoice;

import java.util.Date;

public class ClientGuiState {
    private SkatesChoice skatesChoice;
    private Skates skates;
    private Date skatesServiceDateStart;
    private Date skatesServiceDateEnd;
    private SkatesState skatesStateAfterService;
    private boolean skatesServiceIsSharpening = false;
    private boolean skatesServiceIsRepairing = false;

    public ClientGuiState() {
    }

    public Skates getSkates() {
        return skates;
    }

    public void setSkates(Skates skates) {
        this.skates = skates;
    }

    public Date getSkatesServiceDateStart() {
        return skatesServiceDateStart;
    }

    public void setSkatesServiceDateStart(Date skatesServiceDateStart) {
        this.skatesServiceDateStart = skatesServiceDateStart;
    }

    public Date getSkatesServiceDateEnd() {
        return skatesServiceDateEnd;
    }

    public void setSkatesServiceDateEnd(Date skatesServiceDateEnd) {
        this.skatesServiceDateEnd = skatesServiceDateEnd;
    }

    public SkatesState getSkatesStateAfterService() {
        return skatesStateAfterService;
    }

    public void setSkatesStateAfterService(SkatesState skatesStateAfterService) {
        this.skatesStateAfterService = skatesStateAfterService;
    }

    public boolean isSkatesServiceIsSharpening() {
        return skatesServiceIsSharpening;
    }

    public void setSkatesServiceIsSharpening(boolean skatesServiceIsSharpening) {
        this.skatesServiceIsSharpening = skatesServiceIsSharpening;
    }

    public boolean isSkatesServiceIsRepairing() {
        return skatesServiceIsRepairing;
    }

    public void setSkatesServiceIsRepairing(boolean skatesServiceIsRepairing) {
        this.skatesServiceIsRepairing = skatesServiceIsRepairing;
    }
}
