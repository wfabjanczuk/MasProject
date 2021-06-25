import generator.DataGenerator;
import mas.gui.ClientGui;

public class Main {
    public static void main(String[] args) {
        DataGenerator.main(args);

        ClientGui clientGui = new ClientGui();
        clientGui.initialize();
    }
}
