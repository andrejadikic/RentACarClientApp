package gui.fx.app;

import gui.fx.app.view.LoginView;
import gui.fx.app.view.ReservationView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage mainStage;
    public static Stage secondStage;

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = new Stage();
        secondStage = new Stage();
        Parent borderPane = FXMLLoader.load(ClientApp.registrationUrl);
        Scene sc = new Scene(borderPane, 1000, 500);
        mainStage.setTitle("SK-2");
        mainStage.setScene(sc);
        mainStage.show();
    }


}
