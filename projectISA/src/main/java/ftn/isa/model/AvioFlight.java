package ftn.isa.model;

import java.time.LocalDateTime;
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

@Entity
public class AvioFlight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private LocalDateTime dateTimeStart;
	private LocalDateTime dateTimeFinish;
	private double flightDuration;
	private double flightDistance;
	private double price;
	private boolean isDeleted;
	private int economyClassSeats;
	private int businessClassSeats;
	private int firstClassSeats;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private AvioCompany avioCompany;

	@ElementCollection
	@MapKeyColumn(name = "user", unique = true, nullable = false)
	@Column(name = "rate", nullable = false)
	@CollectionTable(name = "avio_flight_rates", joinColumns = @JoinColumn(name = "id"))
	private Map<Long, Integer> rates = new HashMap<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Address startLocation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Address endLocation;

	public AvioFlight() {
	}

	public AvioFlight(LocalDateTime dateTimeStart, LocalDateTime dateTimeFinish, double flightDuration,
			double flightDistance, double price) {
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public int getEconomyClassSeats() {
		return economyClassSeats;
	}

	public void setEconomyClassSeats(int economyClassSeats) {
		this.economyClassSeats = economyClassSeats;
	}

	public int getBusinessClassSeats() {
		return businessClassSeats;
	}

	public void setBusinessClassSeats(int businessClassSeats) {
		this.businessClassSeats = businessClassSeats;
	}

	public int getFirstClassSeats() {
		return firstClassSeats;
	}

	public void setFirstClassSeats(int firstClassSeats) {
		this.firstClassSeats = firstClassSeats;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
