package gui.fx.app.controller;

import gui.fx.app.restclient.UserServiceRestClient;
import gui.fx.app.restclient.dto.ClientCreateDto;
import gui.fx.app.restclient.dto.ClientDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
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
            userServiceRestClient.updateClient(clientCreateDto);
            System.out.println("Updated");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
