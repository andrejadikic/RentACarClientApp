package gui.fx.app.controller;

import gui.fx.app.restclient.NotificationServiceRestClient;
import gui.fx.app.restclient.ReservationServiceRestClient;
import gui.fx.app.restclient.UserServiceRestClient;
import gui.fx.app.restclient.dto.AccommodationDto;
import gui.fx.app.restclient.dto.ReservationDto;
import gui.fx.app.restclient.dto.UserDto;
import gui.fx.app.restclient.dto.VehicleDto;
import gui.fx.app.view.ReservationView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private TextField typeDeleteTxt;

    @FXML
    private TextField typeTxt;

    @FXML
    private ListView<UserDto> usersView;

    private ObservableList<UserDto> userList= FXCollections.observableArrayList();
    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();
    private NotificationServiceRestClient notificationServiceRestClient = new NotificationServiceRestClient();


    public void ban(ActionEvent event) {
        try {
            userServiceRestClient.banUser(usersView.getSelectionModel().getSelectedItem().getUsername(),true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(ActionEvent event){
        try {
            notificationServiceRestClient.addType(typeTxt.getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(ActionEvent event) {
        try {
            notificationServiceRestClient.deleteType(typeTxt.getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getUsers(ActionEvent event) {
        try {
            userList.clear();
            userList.addAll(userServiceRestClient.getUsers().getContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usersView.setItems(userList);
    }

    public void unban(ActionEvent event) {
        try {
            userServiceRestClient.banUser(usersView.getSelectionModel().getSelectedItem().getUsername(),false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
