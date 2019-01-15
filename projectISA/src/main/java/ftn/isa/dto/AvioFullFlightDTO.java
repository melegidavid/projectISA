package ftn.isa.dto;

import ftn.isa.model.AvioFullFlight;

public class AvioFullFlightDTO {

	private Long id;
	private String dateTimeStart;
	private String dateTimeFinish;
	private double fullFlightDuration;
	private double fullFlightDistance;
	private double fullPrice;
	
	
	public AvioFullFlightDTO() {}
	
	public AvioFullFlightDTO(AvioFullFlight avioFullFlight) {
		this.id = avioFullFlight.getId();
		this.dateTimeStart = avioFullFlight.getDateTimeStart();
		this.dateTimeFinish = avioFullFlight.getDateTimeFinish();
		this.fullFlightDuration = avioFullFlight.getFullFlightDuration();
		this.fullFlightDistance = avioFullFlight.getFullFlightDistance();
		this.fullPrice = avioFullFlight.getFullPrice();
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
	public double getFullFlightDuration() {
		return fullFlightDuration;
	}
	public void setFullFlightDuration(double fullFlightDuration) {
		this.fullFlightDuration = fullFlightDuration;
	}
	public double getFullFlightDistance() {
		return fullFlightDistance;
	}
	public void setFullFlightDistance(double fullFlightDistance) {
		this.fullFlightDistance = fullFlightDistance;
	}
	public double getFullPrice() {
		return fullPrice;
	}
	public void setFullPrice(double fullPrice) {
		this.fullPrice = fullPrice;
	}
	
	
}
