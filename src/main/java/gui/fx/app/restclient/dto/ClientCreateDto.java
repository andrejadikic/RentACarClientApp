package gui.fx.app.restclient.dto;

import javax.validation.constraints.NotBlank;

public class ClientCreateDto extends UserCreateDto{
	@NotBlank
    private String passportNumber;

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	@Override
	public String toString() {
		return "ClientCreateDto{" +
				super.toString()+
				"passportNumber='" + passportNumber + '\'' +
				'}';
	}
}
