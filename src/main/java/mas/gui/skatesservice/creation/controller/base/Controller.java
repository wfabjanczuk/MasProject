package mas.gui.skatesservice.creation.controller.base;

import mas.gui.skatesservice.creation.ClientGui;
import mas.gui.skatesservice.creation.ClientGuiState;

abstract public class Controller {
    protected static ClientGui clientGui;
    protected static ClientGuiState clientGuiState;

    public static void setClientGui(ClientGui clientGui) {
        Controller.clientGui = clientGui;
        Controller.clientGuiState = clientGui.getState();
    }
}
