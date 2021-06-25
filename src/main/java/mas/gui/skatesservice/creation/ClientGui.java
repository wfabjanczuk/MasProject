package mas.gui.skatesservice.creation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mas.gui.skatesservice.creation.controller.base.Controller;
import util.HibernateUtil;

import java.io.IOException;

public class ClientGui extends Application {
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
        setScene(primaryStage, ClientGuiRouter.templateIntro, "Intro");
    }

    public void setConfirmSkatesScene() throws IOException {
        setScene(primaryStage, ClientGuiRouter.templateConfirmSkates, "Dodaj przegląd: Łyżwy");
    }

    public void setTimeScene() throws IOException {
        setScene(primaryStage, ClientGuiRouter.templateSetTime, "Dodaj przegląd: Czas trwania");
    }

    public void setNewerServiceScene() throws IOException {
        setScene(primaryStage, ClientGuiRouter.templateNewerService, "Ostrzeżenie: Istnieje nowszy przegląd");
    }

    public void setSkatesBookingsScene() throws IOException {
        setScene(primaryStage, ClientGuiRouter.templateSkatesBookings, "Ostrzeżenie: Łyżwy są zarezerwowane");
    }

    public void setDetailsScene() throws IOException {
        setScene(primaryStage, ClientGuiRouter.templateSetDetails, "Dodaj przegląd: Szczegóły");
    }

    public void setSuccessScene() throws IOException {
        setScene(primaryStage, ClientGuiRouter.templateSuccess, "Sukces: zapisano przegląd");
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
