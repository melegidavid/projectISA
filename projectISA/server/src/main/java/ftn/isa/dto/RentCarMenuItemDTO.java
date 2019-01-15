package ftn.isa.dto;

import ftn.isa.model.RentCar;
import ftn.isa.model.RentCarMenuItem;

public class RentCarMenuItemDTO {

	private Long id;
	private String serviceName;
	private double price;
	private String description;
	private RentCarDTO rentCarDTO;
	
	
	public RentCarMenuItemDTO() {}

	public RentCarMenuItemDTO(Long id, String serviceName, double price, String description, RentCar rentCar) {
		super();
		this.id = id;
		this.serviceName = serviceName;
		this.price = price;
		this.description = description;
		this.rentCarDTO = new RentCarDTO(rentCar);
	}
	
	public RentCarMenuItemDTO(RentCarMenuItem menuItem) {
		super();
		this.id = menuItem.getId();
		this.serviceName = menuItem.getServiceName();
		this.price = menuItem.getPrice();
		this.description = menuItem.getDescription();
		this.rentCarDTO = new RentCarDTO(menuItem.getRentCar());
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

	public RentCarDTO getRentCarDTO() {
		return rentCarDTO;
	}

	public void setRentCarDTO(RentCarDTO rentCarDTO) {
		this.rentCarDTO = rentCarDTO;
	}
	
}
