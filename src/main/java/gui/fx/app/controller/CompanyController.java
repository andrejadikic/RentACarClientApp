package gui.fx.app.controller;

import gui.fx.app.restclient.ReservationServiceRestClient;
import gui.fx.app.restclient.dto.CompanyDto;
import gui.fx.app.restclient.dto.VehicleDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CompanyController {
    private ReservationServiceRestClient reservationServiceRestClient= new ReservationServiceRestClient();
    @FXML
    TextField txtName;
    @FXML
    TextArea txtInfo;

    public void create(ActionEvent event) {
        CompanyDto vehicleDto = new CompanyDto(txtName.getText(),txtInfo.getText());
        try {
            reservationServiceRestClient.addCompany(vehicleDto);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
