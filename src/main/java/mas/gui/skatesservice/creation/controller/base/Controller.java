package mas.gui.skatesservice.creation.controller.base;

import mas.gui.skatesservice.creation.ClientGui;
import mas.gui.skatesservice.creation.ClientGuiState;

import java.text.SimpleDateFormat;

abstract public class Controller {
    protected static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    protected static final SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    protected static ClientGui clientGui;
    protected static ClientGuiState clientGuiState;

    public static void setClientGui(ClientGui clientGui) {
        Controller.clientGui = clientGui;
        Controller.clientGuiState = clientGui.getState();
    }
}
