package ftn.isa.dto;

import ftn.isa.model.AvioFlightSeat;

public class AvioFlightSeatDTO {

	private Long id;
	private boolean free;
	private int number;
	private String classOfSeat;
	private boolean deleted;
	
	public AvioFlightSeatDTO() {}
	
	public AvioFlightSeatDTO(AvioFlightSeat seat) {
		this.id = seat.getId();
		this.free = seat.isFree();
		this.number =seat.getNumber();
		this.classOfSeat = seat.getClassOfSeat();
		this.deleted = seat.isDeleted();
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
