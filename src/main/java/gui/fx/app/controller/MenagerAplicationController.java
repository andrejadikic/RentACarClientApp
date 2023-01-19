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
        mainPage.getChildren().setAll(Main.companyScene);
    }
    @FXML
    public void newCityMenu(ActionEvent event) {
        mainPage.getChildren().setAll(Main.cityScene);
    }
    @FXML
    public void newModelMenu(ActionEvent event) {
        mainPage.getChildren().setAll(Main.modelScene);
    }
    @FXML
    public void newVehicleMenu(ActionEvent event) {
        mainPage.getChildren().setAll(Main.vehicleScene);
    }
    @FXML
    public void newTypeMenu(ActionEvent event) {
        mainPage.getChildren().setAll(Main.reservationScene);
    }
    @FXML
    public void openCompanyMenu(ActionEvent event) {
        mainPage.getChildren().setAll(Main.companyScene);
    }
    @FXML
    public void openCityMenu(ActionEvent event) {
        mainPage.getChildren().setAll(Main.cityScene);
    }
    @FXML
    public void openModelMenu(ActionEvent event) {
        mainPage.getChildren().setAll(Main.modelScene);
    }
    @FXML
    public void openVehicleMenu(ActionEvent event) {
        mainPage.getChildren().setAll(Main.vehicleScene);
    }
    @FXML
    public void openTypeMenu(ActionEvent event) {
        mainPage.getChildren().setAll(Main.typeScene);
    }
    @FXML
    public void openUserMenu(ActionEvent event) {
        mainPage.getChildren().setAll(Main.reservationScene);
    }
    @FXML
    public void openAdminMenu(ActionEvent event) {
        mainPage.getChildren().setAll(Main.adminScene);
    }
    @FXML
    public void profileMenu(ActionEvent event) {
        mainPage.getChildren().setAll(Main.userUpdateScene);
    }


}
