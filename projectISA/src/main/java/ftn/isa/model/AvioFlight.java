package ftn.isa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AvioFlight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String dateTimeStart;
	private String dateTimeFinish;
	private double flightDuration;
	private double flightDistance;
	private int  numberOfTransfers; //broj presedanja
	//private List<String> locationOfTransfers;
	private double price;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private AvioCompany avioCompany;
	
	public AvioFlight() {}

	public AvioFlight(String dateTimeStart, String dateTimeFinish, double flightDuration, double flightDistance,
			int numberOfTransfers, double price) {
		this.dateTimeStart = dateTimeStart;
		this.dateTimeFinish = dateTimeFinish;
		this.flightDuration = flightDuration;
		this.flightDistance = flightDistance;
		this.numberOfTransfers = numberOfTransfers;
		this.price = price;
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

	public int getNumberOfTransfers() {
		return numberOfTransfers;
	}

	public void setNumberOfTransfers(int numberOfTransfers) {
		this.numberOfTransfers = numberOfTransfers;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public AvioCompany getAvioCompany() {
		return avioCompany;
	}

	public void setAvioCompany(AvioCompany avioCompany) {
		this.avioCompany = avioCompany;
	}
	
	
	
	
}
