package ftn.isa.dto;

public class AvioFlightReservationDTO {

	private Long id;
	private AvioFlightDTO avioFlight;
	private UserDTO user;
	private int flightRating;
	private int avioRating;

	public AvioFlightReservationDTO() {
	}

	public AvioFlightReservationDTO(Long id, AvioFlightDTO avioFlight, UserDTO user, int flightRating,
			int avioRating) {
		this.id = id;
		this.avioFlight = avioFlight;
		this.user = user;
		this.flightRating = flightRating;
		this.avioRating = avioRating;
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

}
