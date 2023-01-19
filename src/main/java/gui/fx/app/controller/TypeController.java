package gui.fx.app.controller;

import gui.fx.app.restclient.ReservationServiceRestClient;
import gui.fx.app.restclient.dto.TypeDto;
import gui.fx.app.restclient.dto.VehicleDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TypeController {
    private ReservationServiceRestClient reservationServiceRestClient= new ReservationServiceRestClient();
    @FXML
    TextField txtType;

    public void create(ActionEvent event) {
        TypeDto vehicleDto = new TypeDto(txtType.getText());
        try {
            reservationServiceRestClient.addType(vehicleDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
