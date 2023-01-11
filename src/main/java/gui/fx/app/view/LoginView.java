package gui.fx.app.view;

import gui.fx.app.Main;
import gui.fx.app.controller.LoginController;
import gui.fx.app.controller.RegisterClientController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class LoginView extends GridPane {

    private Label lblUsername;
    private Label lblPassword;
    private TextField tfUsername;
    private TextField tfPassword;
    private Button btnLogin;
    private Button btnRegisterClient;

    public LoginView(){
        initViewElements();
        addViewElements();
        addListeners();
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5,5,5,5));
        this.setHgap(10);
        this.setVgap(10);
    }

    private void addListeners() {
        btnLogin.setOnAction(new LoginController(tfUsername, tfPassword));
        btnRegisterClient.setOnAction(e->{
            Scene sc = new Scene(new RegisterClientView(), 400, 400);
            Main.secondStage.setScene(sc);
            Main.secondStage.show();
            Main.secondStage.setTitle("Registration for client");
        });
    }

    private void addViewElements() {
        this.addRow(0, lblUsername, tfUsername);
        this.addRow(1, lblPassword, tfPassword);
        this.addRow(2, btnLogin);
        this.addRow(5, btnRegisterClient);
    }

    private void initViewElements() {
        this.lblUsername = new Label("Username:");
        this.lblPassword = new Label("Password:");
        this.tfUsername = new TextField();
        this.tfPassword = new TextField();
        this.btnLogin = new Button("Login");
        this.btnRegisterClient = new Button("Register as client");
    }
}
