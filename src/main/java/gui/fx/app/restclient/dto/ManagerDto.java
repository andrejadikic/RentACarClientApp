package gui.fx.app.restclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ManagerDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String company;
}
