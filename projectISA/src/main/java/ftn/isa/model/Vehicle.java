package ftn.isa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import ftn.isa.enums.VehicleType;

@Entity
@Where(clause="deleted=0")
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
	private int price;
	private boolean deleted;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private RentCar rentCar;
	

	@OneToMany(mappedBy = "belongsToVehicle",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<VehicleReservation> reseravations = new ArrayList<>();
	
	public Vehicle() {
		
	}

	public Vehicle(VehicleType type, int seatsNumber, String name, String mark, String model, int yearProduced,
			RentCarBranch returnPlace, RentCar rentCar) {
		super();
		this.type = type;
		this.seatsNumber = seatsNumber;
		this.name = name;
		this.mark = mark;
		this.model = model;
		this.yearProduced = yearProduced;
		this.rentCar = rentCar;
		this.deleted = false;
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

	public List<VehicleReservation> getReseravations() {
		return reseravations;
	}

	public void setReseravations(List<VehicleReservation> reseravations) {
		this.reseravations = reseravations;
	}

	public RentCar getRentCar() {
		return rentCar;
	}

	public void setRentCar(RentCar rentCar) {
		this.rentCar = rentCar;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
