package ftn.isa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String startReservation;
	private String endReseravtion;
	
	public Reservation() {
		
	}

	public Reservation(String startReservation, String endReseravtion) {
		super();
		this.startReservation = startReservation;
		this.endReseravtion = endReseravtion;
	}

	public String getStartReservation() {
		return startReservation;
	}

	public void setStartReservation(String startReservation) {
		this.startReservation = startReservation;
	}

	public String getEndReseravtion() {
		return endReseravtion;
	}

	public void setEndReseravtion(String endReseravtion) {
		this.endReseravtion = endReseravtion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
