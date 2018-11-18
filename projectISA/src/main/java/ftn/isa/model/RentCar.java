package ftn.isa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RentCar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String address;
	private String description;
	/*private List<RentCarMenuItem> rentCarMenu;
	private List<Vehicle> vehicles;
	private List<RentCarBranch> branches;*/
	
	public RentCar() {
		//STAVITI INICIJALIZACIJU LISTA
	}

	public RentCar(String name, String address, String description/*, List<RentCarMenuItem> rentCarMenu,
			List<Vehicle> vehicles, List<RentCarBranch> branches*/) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		/*this.rentCarMenu = rentCarMenu;
		this.vehicles = vehicles;
		this.branches = branches;*/
		//STAVITI INICIJALIZACIJU LISTA
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public List<RentCarMenuItem> getRentCarMenu() {
		return rentCarMenu;
	}

	public void setRentCarMenu(List<RentCarMenuItem> rentCarMenu) {
		this.rentCarMenu = rentCarMenu;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public List<RentCarBranch> getBranches() {
		return branches;
	}

	public void setBranches(List<RentCarBranch> branches) {
		this.branches = branches;
	}*/
	
}
