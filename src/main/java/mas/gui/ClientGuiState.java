package mas.gui;

import mas.entity.Skates;
import mas.entity.SkatesService;
import mas.entity.SkatesState;

import java.util.Date;

public class ClientGuiState {
    private Skates skates;
    private Date skatesServiceDateStart;
    private Date skatesServiceDateEnd;
    private SkatesState skatesStateAfterService;
    private boolean skatesServiceSharpening = false;
    private boolean skatesServiceRepairing = false;

    private SkatesService newestService;

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

    public boolean isSkatesServiceSharpening() {
        return skatesServiceSharpening;
    }

    public void setSkatesServiceSharpening(boolean skatesServiceSharpening) {
        this.skatesServiceSharpening = skatesServiceSharpening;
    }

    public boolean isSkatesServiceRepairing() {
        return skatesServiceRepairing;
    }

    public void setSkatesServiceRepairing(boolean skatesServiceRepairing) {
        this.skatesServiceRepairing = skatesServiceRepairing;
    }

    public SkatesService getNewestService() {
        return newestService;
    }

    public void setNewestService(SkatesService newestService) {
        this.newestService = newestService;
    }

    public void clear() {
        skates = null;
        skatesServiceDateStart = null;
        skatesServiceDateEnd = null;
        skatesStateAfterService = null;
        skatesServiceSharpening = false;
        skatesServiceRepairing = false;
        newestService = null;
    }
}
