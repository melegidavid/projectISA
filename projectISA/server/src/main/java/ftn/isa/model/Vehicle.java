package ftn.isa.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import javax.persistence.OneToMany;

import ftn.isa.enums.VehicleType;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private VehicleType type;
	private int seatsNumber;
	private String name;
	private String mark;
	private String model;
	private int yearProduced;
	private boolean free;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private RentCarBranch returnPlace;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private RentCarBranch rentCarBranch;
	
	@ElementCollection
    @MapKeyColumn(name="user", unique = true, nullable = false)
    @Column(name="rate", nullable = false)
	@CollectionTable(name="vehicle_rates", joinColumns=@JoinColumn(name="id"))
	private Map<Long, Integer> rates = new HashMap<>(); 
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reservation> reseravations = new ArrayList<>();
	
	public Vehicle() {
		
	}

	public Vehicle(VehicleType type, int seatsNumber, String name, String mark, String model, int yearProduced,
			boolean free, RentCarBranch returnPlace) {
		super();
		this.type = type;
		this.seatsNumber = seatsNumber;
		this.name = name;
		this.mark = mark;
		this.model = model;
		this.yearProduced = yearProduced;
		this.free = free;
		this.returnPlace = returnPlace;
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

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public RentCarBranch getReturnPlace() {
		return returnPlace;
	}

	public void setReturnPlace(RentCarBranch returnPlace) {
		this.returnPlace = returnPlace;
	}

	public Map<Long, Integer> getRates() {
		return rates;
	}

	public void setRates(Map<Long, Integer> rates) {
		this.rates = rates;
	}

	public List<Reservation> getReseravations() {
		return reseravations;
	}

	public void setReseravations(List<Reservation> reseravations) {
		this.reseravations = reseravations;
	}

	public RentCarBranch getRentCarBranch() {
		return rentCarBranch;
	}

	public void setRentCarBranch(RentCarBranch rentCarBranch) {
		this.rentCarBranch = rentCarBranch;
	}
}
