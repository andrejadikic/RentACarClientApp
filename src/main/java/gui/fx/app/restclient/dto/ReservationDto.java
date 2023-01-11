package gui.fx.app.restclient.dto;

import java.sql.Date;

public class ReservationDto {

	private String roomType;
	private String hotel;
	private Date startDate;
	private Date endDate;
	private Long UserId;
	
	public ReservationDto() {
		
	}
	
	public ReservationDto(String hotel, String roomType, Date startDate, Date endDate, Long userId) {
		super();
		this.roomType = roomType;
		this.hotel = hotel;
		this.startDate = startDate;
		this.endDate = endDate;
		UserId = userId;
	}
	
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getHotel() {
		return hotel;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getUserId() {
		return UserId;
	}
	public void setUserId(Long userId) {
		UserId = userId;
	}
}
