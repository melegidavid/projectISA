package ftn.isa.dto;

import java.time.LocalDateTime;

import ftn.isa.model.AvioFlight;

public class AvioFlightDTO {

	private Long id;
	private LocalDateTime dateTimeStart;
	private LocalDateTime dateTimeFinish;
	private double flightDuration;
	private double flightDistance;
	private double price;
	private AddressDTO startLocation;
	private AddressDTO endLocation;
	private boolean isDeleted;

	public AvioFlightDTO() {

	}

	public AvioFlightDTO(AvioFlight avioFlight) {
		this.id = avioFlight.getId();
		this.dateTimeStart = avioFlight.getDateTimeStart();
		this.dateTimeFinish = avioFlight.getDateTimeFinish();
		this.flightDuration = avioFlight.getFlightDuration();
		this.flightDistance = avioFlight.getFlightDistance();
		this.price = avioFlight.getPrice();
		this.startLocation = new AddressDTO(avioFlight.getStartLocation());
		this.endLocation = new AddressDTO(avioFlight.getEndLocation());
		this.isDeleted = avioFlight.isDeleted();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(LocalDateTime dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public LocalDateTime getDateTimeFinish() {
		return dateTimeFinish;
	}

	public void setDateTimeFinish(LocalDateTime dateTimeFinish) {
		this.dateTimeFinish = dateTimeFinish;
	}

	public double getFlightDuration() {
		return flightDuration;
	}

	public void setFlightDuration(double flightDuration) {
		this.flightDuration = flightDuration;
	}

	public double getFlightDistance() {
		return flightDistance;
	}

	public void setFlightDistance(double flightDistance) {
		this.flightDistance = flightDistance;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public AddressDTO getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(AddressDTO startLocation) {
		this.startLocation = startLocation;
	}

	public AddressDTO getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(AddressDTO endLocation) {
		this.endLocation = endLocation;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
