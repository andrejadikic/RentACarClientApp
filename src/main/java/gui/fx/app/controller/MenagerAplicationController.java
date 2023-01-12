package gui.fx.app.controller;

import gui.fx.app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MenagerAplicationController implements Initializable {
    @FXML
    AnchorPane mainPage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainPage.getChildren().setAll(Main.managerUpdateScene);
    }

    @FXML
    void newCompanyMenu(ActionEvent event){

    }
    @FXML
    public void newCityMenu(ActionEvent event) {
    }
    @FXML
    public void newModelMenu(ActionEvent event) {
    }
    @FXML
    public void newVehicleMenu(ActionEvent event) {
    }
    @FXML
    public void newTypeMenu(ActionEvent event) {
    }
    @FXML
    public void openCompanyMenu(ActionEvent event) {
    }
    @FXML
    public void openCityMenu(ActionEvent event) {
    }
    @FXML
    public void openModelMenu(ActionEvent event) {
    }
    @FXML
    public void openVehicleMenu(ActionEvent event) {
    }
    @FXML
    public void openTypeMenu(ActionEvent event) {
    }
    @FXML
    public void profileMenu(ActionEvent event) {
        mainPage.getChildren().setAll(Main.managerUpdateScene);
    }


}
