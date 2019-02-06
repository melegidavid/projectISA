package ftn.isa.dto;

import ftn.isa.model.AvioFlightReservation;
import ftn.isa.model.AvioFlightSeat;

public class AvioFlightReservationDTO {

	private Long id;
	private AvioFlightDTO avioFlight;
	private UserDTO user;
	private int flightRating;
	private int avioRating;
	private boolean isDeleted;
	private AvioFlightSeat seat;

	public AvioFlightReservationDTO() {
	}

	public AvioFlightReservationDTO(AvioFlightReservation reservation) {
		this.id = reservation.getId();
		this.avioFlight = new AvioFlightDTO(reservation.getAvioFlight());
		this.user = new UserDTO(reservation.getUser());
		this.flightRating = reservation.getRatingFlight();
		this.avioRating = reservation.getRatingCompany();
		this.isDeleted = reservation.isDeleted();
		this.seat = reservation.getSeat();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AvioFlightDTO getAvioFlight() {
		return avioFlight;
	}

	public void setAvioFlight(AvioFlightDTO avioFlight) {
		this.avioFlight = avioFlight;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public int getFlightRating() {
		return flightRating;
	}

	public void setFlightRating(int flightRating) {
		this.flightRating = flightRating;
	}

	public int getAvioRating() {
		return avioRating;
	}

	public void setAvioRating(int avioRating) {
		this.avioRating = avioRating;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public AvioFlightSeat getSeat() {
		return seat;
	}

	public void setSeat(AvioFlightSeat seat) {
		this.seat = seat;
	}

}