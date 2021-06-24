package mas.gui;

import mas.model.SkatesChoice;

public class ClientGuiState {
    private SkatesChoice skatesChoice;

    public ClientGuiState() {
    }

    public SkatesChoice getSkatesChoice() {
        return skatesChoice;
    }

    public void setSkatesChoice(SkatesChoice skatesChoice) {
        this.skatesChoice = skatesChoice;
    }
}
