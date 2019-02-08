package ftn.isa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class RentCar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName="id", nullable = false, unique = true)
	private Address address;
	
	private String description;
	
	@OneToMany(mappedBy = "rentCar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<RentCarMenuItem> rentCarMenu = new ArrayList<>();
	
	@OneToMany(mappedBy = "rentCar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<RentCarBranch> branches  = new ArrayList<>(); 
	
	@OneToMany(mappedBy = "rentCar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Vehicle> vehicles = new ArrayList<>();


	
	
	public RentCar() {}

	public RentCar(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RentCarMenuItem> getRentCarMenu() {
		return rentCarMenu;
	}

	public void setRentCarMenu(List<RentCarMenuItem> rentCarMenu) {
		this.rentCarMenu = rentCarMenu;
	}

	public List<RentCarBranch> getBranches() {
		return branches;
	}

	public void setBranches(List<RentCarBranch> branches) {
		this.branches = branches;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
}
