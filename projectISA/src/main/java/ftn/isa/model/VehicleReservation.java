package ftn.isa.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class VehicleReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date startReservation;
	private Date endReseravtion;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private RentCarBranch takePlace;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private RentCarBranch returnPlace;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Vehicle belongsToVehicle;
	
	private int vehicleRating = -1;
	private int rentCarRating = -1;
	
	public VehicleReservation() {
		
	}

	public VehicleReservation(Date startReservation, Date endReseravtion) {
		super();
		this.startReservation = startReservation;
		this.endReseravtion = endReseravtion;
	}

	public Date getStartReservation() {
		return startReservation;
	}

	public void setStartReservation(Date startReservation) {
		this.startReservation = startReservation;
	}

	public Date getEndReseravtion() {
		return endReseravtion;
	}

	public void setEndReseravtion(Date endReseravtion) {
		this.endReseravtion = endReseravtion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Vehicle getBelongsToVehicle() {
		return belongsToVehicle;
	}

	public void setBelongsToVehicle(Vehicle belongsToVehicle) {
		this.belongsToVehicle = belongsToVehicle;
	}

	public RentCarBranch getTakePlace() {
		return takePlace;
	}

	public void setTakePlace(RentCarBranch takePlace) {
		this.takePlace = takePlace;
	}

	public RentCarBranch getReturnPlace() {
		return returnPlace;
	}

	public void setReturnPlace(RentCarBranch returnPlace) {
		this.returnPlace = returnPlace;
	}

	public int getVehicleRating() {
		return vehicleRating;
	}

	public void setVehicleRating(int vehicleRating) {
		this.vehicleRating = vehicleRating;
	}

	public int getRentCarRating() {
		return rentCarRating;
	}

	public void setRentCarRating(int rentCarRating) {
		this.rentCarRating = rentCarRating;
	}
}
