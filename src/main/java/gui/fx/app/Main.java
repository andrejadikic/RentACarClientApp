package gui.fx.app;

import gui.fx.app.view.LoginView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    public static Stage mainStage;
    public static Stage secondStage;
    public static Scene loginScene;
    public static Scene registerScene;
    public static Scene managerRegistrationScene;

    public static Parent reservationScene;
    public static Parent adminScene;


    public static Parent userUpdateScene;
    public static Parent managerUpdateScene;
    public static Parent cityScene;
    public static Parent companyScene;
    public static Parent modelScene;
    public static Parent typeScene;
    public static Parent vehicleScene;
    public static Scene managerAppScene;


    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        initScenes();
        mainStage.setTitle("SK-2");
        mainStage.setScene(managerAppScene);
        mainStage.show();
    }

    private void initScenes() throws Exception {
        mainStage = new Stage();
        secondStage = new Stage();

        loginScene = new Scene(FXMLLoader.load(ClientApp.loginUrl), 1000, 500);
        registerScene = new Scene(FXMLLoader.load(ClientApp.registrationUrl));
        managerRegistrationScene = new Scene(FXMLLoader.load(ClientApp.registrationManagerUrl));

        userUpdateScene = FXMLLoader.load(ClientApp.userUpdateUrl) ;
        managerUpdateScene = FXMLLoader.load(ClientApp.managerUpdateUrl);

        cityScene = FXMLLoader.load(ClientApp.newCityUrl);
        companyScene = FXMLLoader.load(ClientApp.newCompanyUrl);
        modelScene = FXMLLoader.load(ClientApp.newModelUrl);
        typeScene = FXMLLoader.load(ClientApp.newTypeUrl);
        vehicleScene = FXMLLoader.load(ClientApp.newVehicleUrl);

        adminScene = FXMLLoader.load(ClientApp.adminUrl);
        reservationScene = FXMLLoader.load(ClientApp.reservationUrl);


        managerAppScene = new Scene(FXMLLoader.load(ClientApp.managerAppUrl));

    }


}
