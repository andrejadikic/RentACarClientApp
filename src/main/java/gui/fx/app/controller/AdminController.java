package gui.fx.app.controller;

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
import javafx.scene.control.*;

import java.io.IOException;

public class AdminController{
    @FXML
    private TextField typeDeleteTxt;

    @FXML
    private TextField typeTxt;

    @FXML
    private ListView<UserDto> usersView;

    private ObservableList<UserDto> userList= FXCollections.observableArrayList();
    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();


    public void ban(ActionEvent event) {
        try {
            userServiceRestClient.banUser(usersView.getSelectionModel().getSelectedItem().getUsername());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(ActionEvent event) {

    }

    public void delete(ActionEvent event) {
    }

    public void getUsers(ActionEvent event) {
        try {
            userList.addAll(userServiceRestClient.getUsers().getContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
