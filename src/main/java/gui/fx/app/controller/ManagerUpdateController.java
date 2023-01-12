package gui.fx.app.controller;

import gui.fx.app.restclient.UserServiceRestClient;
import gui.fx.app.restclient.dto.ClientCreateDto;
import gui.fx.app.restclient.dto.ManagerCreateDto;
import gui.fx.app.restclient.dto.ManagerDto;
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
public class ManagerUpdateController {
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
    PasswordField txtPassword;
    @FXML
    DatePicker txtDateOfBirth;
    @FXML
    TextField txtCompany;
    @FXML
    DatePicker txtStartDate;
    @FXML
    Button registerBtn;
    @FXML
    public void handle(ActionEvent event) {
        ManagerCreateDto managerCreateDto = new ManagerCreateDto();
        managerCreateDto.setFirstName(txtFirstName.getText());
        managerCreateDto.setLastName(txtLastName.getText());
//        clientCreateDto.setEmail(txtEmail.getText());
        managerCreateDto.setEmail("rentcar123service@gmail.com");
        managerCreateDto.setUsername(txtUsername.getText());
        managerCreateDto.setPassword(txtPassword.getText());
        managerCreateDto.setDateOfBirth(Date.valueOf(txtDateOfBirth.getValue()));
        managerCreateDto.setStartDate(Date.valueOf(txtStartDate.getValue()));
        managerCreateDto.setPhone(txtPhone.getText());
//        clientCreateDto.setPassportNumber(txtPassport.getText());
        managerCreateDto.setCompany(txtCompany.getText());
        try {
            userServiceRestClient.updateManager(managerCreateDto);
            System.out.println("manager updated");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
