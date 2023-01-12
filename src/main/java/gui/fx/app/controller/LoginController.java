package gui.fx.app.controller;

import gui.fx.app.ClientApp;
import gui.fx.app.Main;
import gui.fx.app.restclient.UserServiceRestClient;
import gui.fx.app.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController implements EventHandler<ActionEvent> {

    private TextField username;
    private TextField password;
    private UserServiceRestClient userServiceRestClient;

    public LoginController(TextField username, TextField password) {
        this.username = username;
        this.password = password;
        userServiceRestClient = new UserServiceRestClient();
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            String token = userServiceRestClient.login(username.getText(), password.getText());
            ClientApp.getInstance().setToken(token);
            Scene sc = new Scene(Main.managerAppScene, 1000, 500);
            Main.mainStage.setScene(sc);
            Main.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
