package ftn.isa.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class HotelMenuItemReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date startReservation;
	private Date endReservation;
	private double price; //cena za sve dane boravka
	
	@Version
	private Long version;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private HotelMenuItem belongsToHotelMenuItem;
	
	public HotelMenuItemReservation() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public HotelMenuItem getBelongsToHotelMenuItem() {
		return belongsToHotelMenuItem;
	}

	public void setBelongsToHotelMenuItem(HotelMenuItem belongsToHotelMenuItem) {
		this.belongsToHotelMenuItem = belongsToHotelMenuItem;
	}

	public Date getStartReservation() {
		return startReservation;
	}

	public void setStartReservation(Date startReservation) {
		this.startReservation = startReservation;
	}

	public Date getEndReservation() {
		return endReservation;
	}

	public void setEndReservation(Date endReservation) {
		this.endReservation = endReservation;
	}
	
}
