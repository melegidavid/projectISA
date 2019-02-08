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
import javax.persistence.Version;

@Entity
public class HotelMenuItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String serviceName;
	private double price;
	private String description;
	private boolean deleted;

	@Version
	private Long version;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Hotel hotel;
	
	@OneToMany(mappedBy = "belongsToHotelMenuItem",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<HotelMenuItemReservation> hotelMenuItemReseravations = new ArrayList<>();
	
	public HotelMenuItem() {
		
	}

	public HotelMenuItem(String serviceName, double price, String description) {
		super();
		this.serviceName = serviceName;
		this.price = price;
		this.description = description;
		this.deleted = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<HotelMenuItemReservation> getHotelMenuItemReseravations() {
		return hotelMenuItemReseravations;
	}

	public void setHotelMenuItemReseravations(List<HotelMenuItemReservation> hotelMenuItemReseravations) {
		this.hotelMenuItemReseravations = hotelMenuItemReseravations;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
