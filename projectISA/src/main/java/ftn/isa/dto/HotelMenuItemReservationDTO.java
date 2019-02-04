package ftn.isa.dto;

import java.util.Date;

import ftn.isa.model.HotelMenuItemReservation;

public class HotelMenuItemReservationDTO {

	private Long id;
	private Date startReservation;
	private Date endReservation;
	private double price;
	private String username;
	private HotelMenuItemDTO item;
	
	public HotelMenuItemReservationDTO() {
		
	}
	
	public HotelMenuItemReservationDTO(HotelMenuItemReservation itemReservation) {
		this.id = itemReservation.getId();
		this.startReservation = itemReservation.getStartReservation();
		this.endReservation = itemReservation.getEndReservation();
		this.price = itemReservation.getPrice();
		this.username = itemReservation.getUser().getUsername();
		this.item = new HotelMenuItemDTO(itemReservation.getBelongsToHotelMenuItem());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public HotelMenuItemDTO getItem() {
		return item;
	}

	public void setItem(HotelMenuItemDTO item) {
		this.item = item;
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
}
