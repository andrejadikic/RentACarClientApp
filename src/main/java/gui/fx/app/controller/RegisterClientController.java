package gui.fx.app.controller;

import gui.fx.app.ClientApp;
import gui.fx.app.Main;
import gui.fx.app.restclient.ReservationServiceRestClient;
import gui.fx.app.restclient.UserServiceRestClient;
import gui.fx.app.restclient.dto.ClientCreateDto;
import gui.fx.app.restclient.dto.ClientDto;
import gui.fx.app.view.RegisterClientView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
public class RegisterClientController  {
    private UserServiceRestClient userServiceRestClient= new UserServiceRestClient();
    @FXML
    TextField txtFirstName;
    @FXML
    TextField txtLastName;
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPhone;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtPassport;
    @FXML
    PasswordField txtPassword;
    @FXML
    DatePicker txtDateOfBirth;

    @FXML
    Button registerBtn;
    @FXML
    public void handle(ActionEvent event) {
        ClientCreateDto clientCreateDto = new ClientCreateDto();
        clientCreateDto.setFirstName(txtFirstName.getText());
        clientCreateDto.setLastName(txtLastName.getText());
//        clientCreateDto.setEmail(txtEmail.getText());
        clientCreateDto.setEmail("rentcar123service@gmail.com");
        clientCreateDto.setUsername(txtUsername.getText());
        clientCreateDto.setPassword(txtPassword.getText());
        clientCreateDto.setDateOfBirth(Date.valueOf(txtDateOfBirth.getValue()));
        clientCreateDto.setPhone(txtPhone.getText());
//        clientCreateDto.setPassportNumber(txtPassport.getText());
        clientCreateDto.setPassportNumber("1132<<211<720");
        try {
            ClientDto client = userServiceRestClient.registerClient(clientCreateDto);
            System.out.println(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
