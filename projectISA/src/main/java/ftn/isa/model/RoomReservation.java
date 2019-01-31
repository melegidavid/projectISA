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
public class RoomReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date startReservation;
	private Date endReseravtion;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private HotelRoom belongsToRoom;
	
	public RoomReservation() {}
	
	public RoomReservation(Date startReservation, Date endReseravtion) {
		super();
		this.startReservation = startReservation;
		this.endReseravtion = endReseravtion;
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

	public Date getEndReseravtion() {
		return endReseravtion;
	}

	public void setEndReseravtion(Date endReseravtion) {
		this.endReseravtion = endReseravtion;
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
	
}
