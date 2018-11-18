package ftn.isa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ftn.isa.enums.VehicleType;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private VehicleType type;
	private int seatsNumber; //broj sedista
	private String name;
	private String mark;
	private String model;
	private int yearProduced;
	private boolean free; // da li je vozilo zauzeto
	private String takeDate;
	private String returnDate;
	private String takePlace;
	private String returnPlace;
	
	public Vehicle() {
		
	}

	public Vehicle(VehicleType type, int seatsNumber, String name, String mark, String model, int yearProduced,
			boolean free, String takeDate, String returnDate, String takePlace, String returnPlace) {
		super();
		this.type = type;
		this.seatsNumber = seatsNumber;
		this.name = name;
		this.mark = mark;
		this.model = model;
		this.yearProduced = yearProduced;
		this.free = free;
		this.takeDate = takeDate;
		this.returnDate = returnDate;
		this.takePlace = takePlace;
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

	public String getTakeDate() {
		return takeDate;
	}

	public void setTakeDate(String takeDate) {
		this.takeDate = takeDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getTakePlace() {
		return takePlace;
	}

	public void setTakePlace(String takePlace) {
		this.takePlace = takePlace;
	}

	public String getReturnPlace() {
		return returnPlace;
	}

	public void setReturnPlace(String returnPlace) {
		this.returnPlace = returnPlace;
	}
	
	
	
}
