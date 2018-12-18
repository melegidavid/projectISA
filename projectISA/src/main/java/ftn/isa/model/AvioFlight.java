package ftn.isa.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;

@Entity
public class AvioFlight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String dateTimeStart;
	private String dateTimeFinish;
	private double flightDuration;
	private double flightDistance;
	private double price;
	
	@ElementCollection
    @MapKeyColumn(name="seat", nullable = false, unique = true)
    @Column(name="free", nullable = false)
	@CollectionTable(name="avio_flight_seats", joinColumns=@JoinColumn(name="id"))
	private Map<Double, Boolean> seats = new HashMap<>(); // 1.20, znaci da je prva klasa sediste broj 20. itd...
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private AvioCompany avioCompany;
	
	@ElementCollection
    @MapKeyColumn(name="user", unique = true, nullable = false)
    @Column(name="rate", nullable = false)
	@CollectionTable(name="avio_flight_rates", joinColumns=@JoinColumn(name="id"))
	private Map<Long, Integer> rates = new HashMap<>(); 
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName="id", nullable = false, unique = true)
	private Address startLocation;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName="id", nullable = false, unique = true)
	private Address endLocation;

	public AvioFlight() {}

	public AvioFlight(String dateTimeStart, String dateTimeFinish, double flightDuration, double flightDistance,
						double price) {
		this.dateTimeStart = dateTimeStart;
		this.dateTimeFinish = dateTimeFinish;
		this.flightDuration = flightDuration;
		this.flightDistance = flightDistance;
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
	
	public Map<Long, Integer> getRates() {
		return rates;
	}
	
	public void setRates(Map<Long, Integer> rates) {
		this.rates = rates;
	}

	public Address getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(Address startLocation) {
		this.startLocation = startLocation;
	}

	public Address getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(Address endLocation) {
		this.endLocation = endLocation;
	}

	public Map<Double, Boolean> getSeats() {
		return seats;
	}

	public void setSeats(Map<Double, Boolean> seats) {
		this.seats = seats;
	}
}
