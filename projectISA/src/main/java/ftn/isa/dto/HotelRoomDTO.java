package ftn.isa.dto;

import ftn.isa.model.HotelRoom;

public class HotelRoomDTO {

	private Long id;
	private String number;
	private boolean free;
	private HotelDTO hotel;
	private String description;
	private double price;
	
	public HotelRoomDTO() {
		
	}
	
	public HotelRoomDTO(HotelRoom hotelRoom) {
		id = hotelRoom.getId();
		number = hotelRoom.getNumber();
		free = hotelRoom.isFree();
		hotel = new HotelDTO(hotelRoom.getHotel());
		description = hotelRoom.getDescription();
		price = hotelRoom.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
