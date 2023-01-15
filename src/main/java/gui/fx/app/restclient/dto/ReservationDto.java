package gui.fx.app.restclient.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDto {
	private String plateNumber;
	private String username;
	private Date startDate;
	private Date endDate;
	private int price;
}

