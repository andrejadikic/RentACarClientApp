package gui.fx.app.controller;

import gui.fx.app.restclient.ReservationServiceRestClient;
import gui.fx.app.restclient.dto.CompanyDto;
import gui.fx.app.restclient.dto.ModelDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ModelController {
    private ReservationServiceRestClient reservationServiceRestClient= new ReservationServiceRestClient();
    @FXML
    TextField txtModel;
    @FXML
    TextField txtType;

    public void create(ActionEvent event) {
        ModelDto vehicleDto = new ModelDto(txtType.getText(),txtModel.getText());
        try {
            reservationServiceRestClient.addModel(vehicleDto);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
