import generator.DataGenerator;
import mas.gui.skatesservice.creation.ClientGui;

public class Main {
    public static void main(String[] args) {
        DataGenerator.main(args);

        ClientGui clientGui = new ClientGui();
        clientGui.initialize();
    }
}
