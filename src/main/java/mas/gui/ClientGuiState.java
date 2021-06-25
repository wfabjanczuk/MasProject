package mas.gui;

import mas.entity.SkatesState;
import mas.model.SkatesChoice;

import java.util.Date;

public class ClientGuiState {
    private SkatesChoice skatesChoice;
    private Date skatesServiceDateStart;
    private Date skatesServiceDateEnd;
    private SkatesState skatesStateAfterService;
    private boolean skatesServiceIsSharpening = false;
    private boolean skatesServiceIsRepairing = false;

    public ClientGuiState() {
    }

    public SkatesChoice getSkatesChoice() {
        return skatesChoice;
    }

    public void setSkatesChoice(SkatesChoice skatesChoice) {
        this.skatesChoice = skatesChoice;
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

    public Boolean getSkatesServiceIsSharpening() {
        return skatesServiceIsSharpening;
    }

    public void setSkatesServiceIsSharpening(Boolean skatesServiceIsSharpening) {
        this.skatesServiceIsSharpening = skatesServiceIsSharpening;
    }

    public Boolean getSkatesServiceIsRepairing() {
        return skatesServiceIsRepairing;
    }

    public void setSkatesServiceIsRepairing(Boolean skatesServiceIsRepairing) {
        this.skatesServiceIsRepairing = skatesServiceIsRepairing;
    }
}
