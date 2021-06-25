import generator.TestDataGenerator;
import mas.gui.ClientGui;

public class Main {
    public static void main(String[] args) {
        TestDataGenerator.main(args);

        ClientGui clientGui = new ClientGui();
        clientGui.initialize();
    }
}
