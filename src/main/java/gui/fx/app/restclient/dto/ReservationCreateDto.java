package gui.fx.app.restclient.dto;

import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ReservationCreateDto {
    @NotBlank
    private String plateNumber;
    @NotBlank
    private long userId;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
}
