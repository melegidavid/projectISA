package ftn.isa.dto;

import ftn.isa.model.HotelRoom;

public class HotelRoomDTO {

	private Long id;
	private String number;
	private boolean free;
	private HotelDTO hotel;
	
	public HotelRoomDTO() {
		
	}
	
	public HotelRoomDTO(HotelRoom hotelRoom) {
		id = hotelRoom.getId();
		number = hotelRoom.getNumber();
		free = hotelRoom.isFree();
		hotel = new HotelDTO(hotelRoom.getHotel());
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
}
