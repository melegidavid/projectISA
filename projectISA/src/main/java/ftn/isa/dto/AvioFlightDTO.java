package ftn.isa.dto;

import ftn.isa.model.AvioFlight;

public class AvioFlightDTO {

	private Long id;
	private String dateTimeStart;
	private String dateTimeFinish;
	private double flightDuration;
	private double flightDistance;
	private double price;
	
	public AvioFlightDTO() {
	
	}
	
	public AvioFlightDTO(AvioFlight avioFlight) {
		this.id = avioFlight.getId();
		this.dateTimeStart = avioFlight.getDateTimeStart();
		this.dateTimeFinish = avioFlight.getDateTimeFinish();
		this.flightDuration = avioFlight.getFlightDuration();
		this.flightDistance = avioFlight.getFlightDistance();
		this.price = avioFlight.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(String dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public String getDateTimeFinish() {
		return dateTimeFinish;
	}

	public void setDateTimeFinish(String dateTimeFinish) {
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
}
