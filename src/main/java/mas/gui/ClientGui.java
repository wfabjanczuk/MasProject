package mas.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mas.gui.controller.base.Controller;
import util.HibernateUtil;

import java.io.IOException;

public class ClientGui extends Application {
    private static final String templateIntro = "template/intro.fxml";
    private static final String templateConfirmSkates = "template/confirm-skates.fxml";
    private static final String templateSetTime = "template/set-time.fxml";
    private static final String templateNewerService = "template/newer-service.fxml";
    private static final String templateSetDetails = "template/set-details.fxml";
    private static final String templateSuccess = "template/success.fxml";

    private static Stage primaryStage;
    private static ClientGuiState clientGuiState;

    public void initialize() {
        clientGuiState = new ClientGuiState();
        Controller.setClientGui(this);
        launch();
    }

    public ClientGuiState getState() {
        return clientGuiState;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ClientGui.primaryStage = primaryStage;
        clearStateAndSetIntroScene();
    }

    public void clearStateAndSetIntroScene() throws IOException {
        clientGuiState.clear();
        setScene(primaryStage, templateIntro, "Intro");
    }

    public void setConfirmSkatesScene() throws IOException {
        setScene(primaryStage, templateConfirmSkates, "Dodaj przegląd: Łyżwy");
    }

    public void setTimeScene() throws IOException {
        setScene(primaryStage, templateSetTime, "Dodaj przegląd: Czas trwania");
    }

    public void setNewerServiceScene() throws IOException {
        setScene(primaryStage, templateNewerService, "Ostrzeżenie: Istnieje nowszy przegląd");
    }

    public void setDetailsScene() throws IOException {
        setScene(primaryStage, templateSetDetails, "Dodaj przegląd: Szczegóły");
    }

    public void setSuccessScene() throws IOException {
        setScene(primaryStage, templateSuccess, "Sukces: zapisano przegląd");
    }

    private void setScene(Stage primaryStage, String template, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource(template));

        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();
    }

    @Override
    public void stop() {
        HibernateUtil.closeSessionFactory();
    }
}
