package ftn.isa.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AvioFlightSearchDTO {

	private String typeOfFlight;
	private AddressDTO fromLocation;
	private AddressDTO toLocation;
	private LocalDate departureDate;
	private LocalDate returnDate;
	private int travelers;
	private String classFlight;

	public AvioFlightSearchDTO() {
	}

	public AvioFlightSearchDTO(AddressDTO fromLocation, 
							   AddressDTO toLocation, 
							   LocalDateTime departureDate,
							   LocalDateTime returnDate, 
							   int travelers, 
							   String classFlight,
							   String typeOfFlight) {
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.departureDate = departureDate.toLocalDate();
		this.returnDate = returnDate.toLocalDate();
		this.travelers = travelers;
		this.classFlight = classFlight;
		this.typeOfFlight = typeOfFlight;
	}

	public AddressDTO getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(AddressDTO fromLocation) {
		this.fromLocation = fromLocation;
	}

	public AddressDTO getToLocation() {
		return toLocation;
	}

	public void setToLocation(AddressDTO toLocation) {
		this.toLocation = toLocation;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public int getTravelers() {
		return travelers;
	}

	public void setTravelers(int travelers) {
		this.travelers = travelers;
	}

	public String getClassFlight() {
		return classFlight;
	}

	public void setClassFlight(String classFlight) {
		this.classFlight = classFlight;
	}

	public String getTypeOfFlight() {
		return typeOfFlight;
	}

	public void setTypeOfFlight(String typeOfFlight) {
		this.typeOfFlight = typeOfFlight;
	}

	
	
	
}
