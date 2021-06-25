package mas.gui.controller.base;

import mas.gui.ClientGui;
import mas.gui.ClientGuiState;

import java.text.SimpleDateFormat;

abstract public class Controller {
    protected static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    protected static ClientGui clientGui;
    protected static ClientGuiState clientGuiState;

    public static void setClientGui(ClientGui clientGui) {
        Controller.clientGui = clientGui;
        Controller.clientGuiState = clientGui.getState();
    }
}
