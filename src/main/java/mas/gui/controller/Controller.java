package mas.gui.controller;

import mas.gui.ClientGui;

abstract public class Controller {
    protected static ClientGui clientGui;

    public static void setClientGui(ClientGui clientGui) {
        Controller.clientGui = clientGui;
    }
}
