package mas.gui;

import mas.model.SkatesChoice;

import java.util.Date;

public class ClientGuiState {
    private SkatesChoice skatesChoice;
    private Date skatesServiceDateStart;
    private Date skatesServiceDateEnd;

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
}
