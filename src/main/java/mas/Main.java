package mas;

import mas.gui.ClientGui;
import mas.test.TestDataCreator;

public class Main {
    public static void main(String[] args) {
//        TestDataCreator.main(args);

        ClientGui clientGui = new ClientGui();
        clientGui.initialize();
    }
}
