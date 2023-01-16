package gui.fx.app.restclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateDto {
    @Min(1)
    @Max(5)
    @NotNull
    private int rating;
    @NotBlank
    private String comment;
    @NotBlank
    private String vehiclePlateNumber;
}
