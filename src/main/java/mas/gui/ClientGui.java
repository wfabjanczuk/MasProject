package mas.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mas.gui.controller.Controller;
import util.HibernateUtil;

import java.io.IOException;
import java.net.URL;

public class ClientGui extends Application {
    private static final String templateIntro = "template/intro.fxml";
    private static Stage primaryStage;

    public void initialize() {
        Controller.setClientGui(this);
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ClientGui.primaryStage = primaryStage;
        setIntroScene();
    }

    public void setIntroScene() throws IOException {
        setScene(primaryStage, templateIntro, "Intro");
    }

    private void setScene(Stage primaryStage, String template, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getResource(template));

        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();
    }

    private URL getResource(String name) {
        return getClass().getClassLoader().getResource(name);
    }

    @Override
    public void stop() {
        HibernateUtil.closeSessionFactory();
    }
}
