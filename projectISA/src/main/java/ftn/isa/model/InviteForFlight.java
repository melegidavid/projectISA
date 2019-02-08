package ftn.isa.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class InviteForFlight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private User user1;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private User user2;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private AvioFlight flight;
	private boolean accepted;
	
	@Version
	private Long version;
	

	public InviteForFlight() {
	}

	public InviteForFlight(Long id, User user1, User user2, AvioFlight flight, boolean accepted) {
		this.id = id;
		this.user1 = user1;
		this.user2 = user2;
		this.flight = flight;
		this.accepted = accepted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public AvioFlight getFlight() {
		return flight;
	}

	public void setFlight(AvioFlight flight) {
		this.flight = flight;
	}

	
}
