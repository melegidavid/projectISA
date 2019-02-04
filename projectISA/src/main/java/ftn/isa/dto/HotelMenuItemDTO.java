package ftn.isa.dto;

import ftn.isa.model.HotelMenuItem;

public class HotelMenuItemDTO {

	private Long id;
	private String serviceName;
	private double price;
	private String description;
	private HotelDTO hotelDTO;
	private boolean selected; //da li je item odabran od strane korisnika (samo za frontend)
	
	public HotelMenuItemDTO() {
		
	}
	
	public HotelMenuItemDTO(HotelMenuItem hotelMenuItem) {
		this.id = hotelMenuItem.getId();
		this.serviceName = hotelMenuItem.getServiceName();
		this.price = hotelMenuItem.getPrice();
		this.description = hotelMenuItem.getDescription();
		this.hotelDTO = new HotelDTO(hotelMenuItem.getHotel()); 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HotelDTO getHotelDTO() {
		return hotelDTO;
	}

	public void setHotelDTO(HotelDTO hotelDTO) {
		this.hotelDTO = hotelDTO;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
