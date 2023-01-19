package gui.fx.app.restclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRatingDto {
    private String companyName;
    private double rating;

    @Override
    public String toString() {
        return "company: " + companyName +
                ", rating: " + rating;
    }
}
