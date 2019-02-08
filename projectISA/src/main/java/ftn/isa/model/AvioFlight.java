package ftn.isa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@OneToMany(mappedBy = "avioFlight",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AvioFlightReservation> reseravations = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	private AvioCompany avioCompany;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Address startLocation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Address endLocation;

	@OneToMany(fetch = FetchType.LAZY)
	private List<AvioFlightSeat> seats = new ArrayList<AvioFlightSeat>();

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

	public List<AvioFlightSeat> getSeats() {
		return seats;
	}

	public void setSeats(List<AvioFlightSeat> seats) {
		this.seats = seats;
	}
	
	public List<AvioFlightReservation> getReseravations() {
		return reseravations;
	}

	public void setReseravations(List<AvioFlightReservation> reseravations) {
		this.reseravations = reseravations;
	}

}