package ftn.isa.dto;

import java.util.Date;

import ftn.isa.enums.VehicleType;

public class VehicleSearchDTO {

	private Long idRentCar;
	private Date takeDate;
	private Date returnDate;
	private Long takePlace;
	private Long returnPlace;
	private VehicleType vehicleType;
	private int numberOfPassengers;
	
	
	public VehicleSearchDTO() {}


	public VehicleSearchDTO(Long idRentCar, Date takeDate, Date returnDate, Long takePlace,
			Long returnPlace, VehicleType vehicleType, int numberOfPassengers) {
		super();
		this.idRentCar = idRentCar;
		this.takeDate = takeDate;
		this.returnDate = returnDate;
		this.takePlace = takePlace;
		this.returnPlace = returnPlace;
		this.vehicleType = vehicleType;
		this.numberOfPassengers = numberOfPassengers;
	}


	public Long getIdRentCar() {
		return idRentCar;
	}


	public void setIdRentCar(Long idRentCar) {
		this.idRentCar = idRentCar;
	}


	public Date getTakeDate() {
		return takeDate;
	}


	public void setTakeDate(Date takeDate) {
		this.takeDate = takeDate;
	}


	public Date getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}


	public Long getTakePlace() {
		return takePlace;
	}


	public void setTakePlace(Long takePlace) {
		this.takePlace = takePlace;
	}


	public Long getReturnPlace() {
		return returnPlace;
	}


	public void setReturnPlace(Long returnPlace) {
		this.returnPlace = returnPlace;
	}


	public VehicleType getVehicleType() {
		return vehicleType;
	}


	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}


	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}


	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
	
	
	
	
	
}
