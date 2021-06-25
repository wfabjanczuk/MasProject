package mas.gui.controller;

import mas.gui.ClientGui;
import mas.gui.ClientGuiState;

abstract public class Controller {
    protected static ClientGui clientGui;
    protected static ClientGuiState clientGuiState;

    public static void setClientGui(ClientGui clientGui) {
        Controller.clientGui = clientGui;
        Controller.clientGuiState = clientGui.getState();
    }
}
