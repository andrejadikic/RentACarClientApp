package gui.fx.app.controller;

import gui.fx.app.ClientApp;
import gui.fx.app.Main;
import gui.fx.app.restclient.UserServiceRestClient;
import gui.fx.app.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor
@AllArgsConstructor
public class LoginController{
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;
    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();

    public void login(ActionEvent event) {
        try {
            String token = userServiceRestClient.login(txtUsername.getText(), txtPassword.getText());
            System.out.println(token);
            ClientApp.getInstance().setToken(token);
            Main.mainStage.setScene(Main.managerAppScene);
            Main.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void register(ActionEvent event) {
            Main.mainStage.setScene(Main.registerScene);
            Main.mainStage.show();

    }

    public void registerM(ActionEvent event) {
            Main.mainStage.setScene(Main.managerRegistrationScene);
            Main.mainStage.show();

    }


}
