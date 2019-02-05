package ftn.isa.dto;

public class RatingUpdateDTO {
	
	private Long idReservation;
	private int newValue;
	
	public RatingUpdateDTO() {}
	
	public RatingUpdateDTO(Long id, int v) {
		this.idReservation = id;
		this.newValue = v;
	}
	
	public Long getIdReservation() {
		return idReservation;
	}
	public void setIdReservation(Long idReservation) {
		this.idReservation = idReservation;
	}
	public int getNewValue() {
		return newValue;
	}
	public void setNewValue(int newValue) {
		this.newValue = newValue;
	}
	
	

}
