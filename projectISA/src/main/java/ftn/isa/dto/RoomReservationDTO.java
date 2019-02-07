package ftn.isa.dto;

import java.util.Date;

import ftn.isa.model.RoomReservation;

public class RoomReservationDTO {

	private Long id;
	private Date startReservation;
	private Date endReservation;
	private String username;
	private double price;
	private HotelRoomDTO room;
	private int hotelRating;
	private int roomRating;
	
	public RoomReservationDTO() {
		
	}
	
	public RoomReservationDTO(Date startReservation, Date endReservation) {
		this.startReservation = startReservation;
		this.endReservation = endReservation;
	}
	
	public RoomReservationDTO(RoomReservation rr) {
		this.id = rr.getId();
		this.startReservation = rr.getStartReservation();
		this.endReservation = rr.getEndReservation();
		this.username = rr.getUser().getUsername();
		this.price = rr.getPrice();
		this.room = new HotelRoomDTO(rr.getBelongsToRoom());
		this.hotelRating = rr.getHotelRating();
		this.roomRating = rr.getRoomRating();
	}

	public int getHotelRating() {
		return hotelRating;
	}

	public void setHotelRating(int hotelRating) {
		this.hotelRating = hotelRating;
	}

	public int getRoomRating() {
		return roomRating;
	}

	public void setRoomRating(int roomRating) {
		this.roomRating = roomRating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartReservation() {
		return startReservation;
	}

	public void setStartReservation(Date startReservation) {
		this.startReservation = startReservation;
	}

	public Date getEndReservation() {
		return endReservation;
	}

	public void setEndReservation(Date endReservation) {
		this.endReservation = endReservation;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public HotelRoomDTO getRoom() {
		return room;
	}

	public void setRoom(HotelRoomDTO room) {
		this.room = room;
	}
}
