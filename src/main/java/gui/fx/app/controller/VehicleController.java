package gui.fx.app.controller;

import gui.fx.app.restclient.ReservationServiceRestClient;
import gui.fx.app.restclient.UserServiceRestClient;
import gui.fx.app.restclient.dto.VehicleDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class VehicleController {
    private ReservationServiceRestClient reservationServiceRestClient= new ReservationServiceRestClient();
    @FXML
    TextField txtVehicle;
    @FXML
    TextField txtModel;
    @FXML
    TextField txtType;
    @FXML
    TextField txtCompany;
    @FXML
    TextField txtCity;
    @FXML
    TextField txtPrice;

    public void create(ActionEvent event) {
        VehicleDto vehicleDto = new VehicleDto(txtVehicle.getText(),txtModel.getText(),txtType.getText(),txtCompany.getText(),txtCity.getText(),Integer.parseInt(txtPrice.getText()));
        try {
            reservationServiceRestClient.addVehicle(vehicleDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
