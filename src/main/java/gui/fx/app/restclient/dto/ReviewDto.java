package gui.fx.app.restclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private int rating;
    @NotBlank
    private String comment;
    @NotBlank
    private String vehiclePlateNumber;
    @NotBlank
    private String username;
}
