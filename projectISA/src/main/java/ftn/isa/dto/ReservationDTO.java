package ftn.isa.dto;

import java.util.Date;

import ftn.isa.model.VehicleReservation;

public class ReservationDTO {
	
	private Long id;
	private String username;  //username korisnika koji vrsi rezervaciju
	private VehicleDTO vehicle;  
	
	private Long returnPlaceId;
	private Long takePlaceId;
	
	private Date startReservation;
	private Date endReservation;
	
	private int vehicleRating;
	private int rentCarRating;
	
	public ReservationDTO() {
		
	}
	
	public ReservationDTO(String username, VehicleDTO v) {
		this.vehicle = v;
		this.username = username;
	}
	
	public ReservationDTO(VehicleReservation vr) {
		this.username = vr.getUser().getUsername();
		this.returnPlaceId = vr.getReturnPlace().getId();
		this.takePlaceId = vr.getTakePlace().getId();
		this.startReservation = vr.getStartReservation();
		this.endReservation = vr.getEndReseravtion();
		this.vehicle = new VehicleDTO(vr.getBelongsToVehicle());
		this.rentCarRating = vr.getRentCarRating();
		this.vehicleRating = vr.getVehicleRating();
		this.setId(vr.getId());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public VehicleDTO getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleDTO v) {
		this.vehicle = v;
	}

	public Long getReturnPlaceId() {
		return returnPlaceId;
	}

	public void setReturnPlaceId(Long returnPlaceId) {
		this.returnPlaceId = returnPlaceId;
	}

	public Long getTakePlaceId() {
		return takePlaceId;
	}

	public void setTakePlaceId(Long takePlaceId) {
		this.takePlaceId = takePlaceId;
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

	public void setEndReservation(Date endReseravtion) {
		this.endReservation = endReseravtion;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
