package ftn.isa.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class RoomReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date startReservation;
	private Date endReservation;
	private double price;
	private int hotelRating;
	private int roomRating;
	
	@Version
	private Long version;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private HotelRoom belongsToRoom;
	
	public RoomReservation() {}
	
	public int getHotelRating() {
		return hotelRating;
	}

	public void setHotelRating(int hotelRating) {
		this.hotelRating = hotelRating;
	}

	public int getRoomRating() {
		return roomRating;
	}

	public void setRoomRating(int roomRating) {
		this.roomRating = roomRating;
	}

	public RoomReservation(Date startReservation, Date endReseravtion, HotelRoom belongsToRoom) {
		super();
		this.startReservation = startReservation;
		this.endReservation = endReseravtion;
		this.belongsToRoom = belongsToRoom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public HotelRoom getBelongsToRoom() {
		return belongsToRoom;
	}

	public void setBelongsToRoom(HotelRoom belongsToRoom) {
		this.belongsToRoom = belongsToRoom;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
