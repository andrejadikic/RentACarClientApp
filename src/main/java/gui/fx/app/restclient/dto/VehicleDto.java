package gui.fx.app.restclient.dto;


import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDto {
    @NotBlank
    private String plateNumber;
    @NotBlank
    private String model;
    @NotBlank
    private String type;
    @NotBlank
    private String company;
    @NotBlank
    private String city;
    @NotNull
    private int pricePerDay;
}
