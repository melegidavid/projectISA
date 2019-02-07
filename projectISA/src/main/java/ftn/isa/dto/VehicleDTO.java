package ftn.isa.dto;

import ftn.isa.enums.VehicleType;
import ftn.isa.model.Vehicle;

public class VehicleDTO {
	
	private Long id;
	private VehicleType type;
	private int seatsNumber;
	private String name;
	private String mark;
	private String model;
	private int yearProduced;
	private int price;
	private RentCarDTO rentCar;
	
	public VehicleDTO() {}
	
	public VehicleDTO(Vehicle vehicle) {
		this.id = vehicle.getId();
		this.type = vehicle.getType();
		this.seatsNumber = vehicle.getSeatsNumber();
		this.name = vehicle.getName();
		this.mark = vehicle.getMark();
		this.model = vehicle.getModel();
		this.yearProduced = vehicle.getYearProduced();
		this.price = vehicle.getPrice();
		
		this.rentCar = new RentCarDTO(vehicle.getRentCar());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public int getSeatsNumber() {
		return seatsNumber;
	}

	public void setSeatsNumber(int seatsNumber) {
		this.seatsNumber = seatsNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYearProduced() {
		return yearProduced;
	}

	public void setYearProduced(int yearProduced) {
		this.yearProduced = yearProduced;
	}

	public RentCarDTO getRentCar() {
		return rentCar;
	}

	public void setRentCar(RentCarDTO rentCar) {
		this.rentCar = rentCar;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
