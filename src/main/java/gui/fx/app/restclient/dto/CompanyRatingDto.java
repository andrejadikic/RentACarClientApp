package gui.fx.app.restclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompanyRatingDto {
    private String companyName;
    private double rating;
}
