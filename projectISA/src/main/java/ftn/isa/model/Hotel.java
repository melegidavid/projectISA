package ftn.isa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String address;
	private String description;
	//private HotelMenuItem menu;
	//private List<HotelRoom> rooms;
	
	public Hotel() {
		
	}

	public Hotel(String name, String address, String description/*, HotelMenu menu, List<HotelRoom> rooms*/) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
	/*	this.menu = menu;
		this.rooms = rooms;*/
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

	/*public HotelMenu getMenu() {
		return menu;
	}

	public void setMenu(HotelMenu menu) {
		this.menu = menu;
	}

	public List<HotelRoom> getRooms() {
		return rooms;
	}

	public void setRooms(List<HotelRoom> rooms) {
		this.rooms = rooms;
	}*/
	
	
	
}
