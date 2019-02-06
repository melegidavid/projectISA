package ftn.isa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AvioFlightSeat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private boolean free;
	private int number;
	private String classOfSeat;
	private boolean deleted;

	public AvioFlightSeat() {
	}

	public AvioFlightSeat(Long id, boolean free, int number, String classOfSeat, boolean deleted) {
		this.id = id;
		this.free = free;
		this.number = number;
		this.classOfSeat = classOfSeat;
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getClassOfSeat() {
		return classOfSeat;
	}

	public void setClassOfSeat(String classOfSeat) {
		this.classOfSeat = classOfSeat;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}