package gui.fx.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage mainStage;
    public static Stage secondStage;
    public static Parent registerScene;
    public static Parent reservationScene;
    public static Parent loginScene;
    public static Parent userAppScene;
    public static Parent adminAppScene;
    public static Parent managerAppScene;

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        initScenes();
        Scene sc = new Scene(managerAppScene, 1000, 500);
        mainStage.setTitle("SK-2");
        mainStage.setScene(sc);
        mainStage.show();
    }

    private void initScenes() throws Exception {
        mainStage = new Stage();
        secondStage = new Stage();
        registerScene = FXMLLoader.load(ClientApp.registrationUrl);
        reservationScene = FXMLLoader.load(ClientApp.reservationUrl);
        managerAppScene = FXMLLoader.load(ClientApp.managerAppUrl);
    }


}
