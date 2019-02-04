package ftn.isa.dto;

import java.util.Date;

public class RoomSearchDTO {
	
	private Date startDate;
	private Date endDate;
	private double startPrice;
	private double endPrice;
	private int guestsNumber;
	private int roomsNumber;
	
	public RoomSearchDTO() {
		
	}
	
	public RoomSearchDTO(Date startDate, Date endDate, double startPrice, double endPrice, int guestsNumber, int roomsNumber) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.startPrice = startPrice;
		this.endPrice = endPrice;
		this.guestsNumber = guestsNumber;
		this.roomsNumber = roomsNumber;
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

	public double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}

	public double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(double endPrice) {
		this.endPrice = endPrice;
	}

	public int getGuestsNumber() {
		return guestsNumber;
	}

	public void setGuestsNumber(int guestsNumber) {
		this.guestsNumber = guestsNumber;
	}

	public int getRoomsNumber() {
		return roomsNumber;
	}

	public void setRoomsNumber(int roomsNumber) {
		this.roomsNumber = roomsNumber;
	}

}
