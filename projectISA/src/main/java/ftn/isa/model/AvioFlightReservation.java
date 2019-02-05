package ftn.isa.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class AvioFlightReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private AvioFlight avioFlight;
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	private int ratingFlight;
	private int ratingCompany;
	private boolean isDeleted;

	public AvioFlightReservation() {
	}

	public AvioFlightReservation(Long id, AvioFlight avioFlight, User user, int ratingFlight, int ratingCompany,
			boolean isDeleted) {
		this.id = id;
		this.avioFlight = avioFlight;
		this.user = user;
		this.ratingFlight = ratingFlight;
		this.ratingCompany = ratingCompany;
		this.isDeleted = isDeleted;

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

	public AvioFlight getAvioFlight() {
		return avioFlight;
	}

	public void setAvioFlight(AvioFlight avioFlight) {
		this.avioFlight = avioFlight;
	}

	public int getRatingFlight() {
		return ratingFlight;
	}

	public void setRatingFlight(int ratingFlight) {
		this.ratingFlight = ratingFlight;
	}

	public int getRatingCompany() {
		return ratingCompany;
	}

	public void setRatingCompany(int ratingCompany) {
		this.ratingCompany = ratingCompany;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
