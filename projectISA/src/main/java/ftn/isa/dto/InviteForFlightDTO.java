package ftn.isa.dto;

import ftn.isa.model.InviteForFlight;

public class InviteForFlightDTO {

	private Long id;
	private UserDTO userDTO1;
	private UserDTO userDTO2;
	private AvioFlightDTO avioFlightDTO;
	private boolean accepted;
	
	public InviteForFlightDTO() {}

	public InviteForFlightDTO(InviteForFlight invite) {
		this.id = invite.getId();
		this.userDTO1 = new UserDTO(invite.getUser1());
		this.userDTO2 = new UserDTO(invite.getUser2());
		this.avioFlightDTO = new AvioFlightDTO(invite.getFlight());
		this.accepted = invite.isAccepted();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUserDTO1() {
		return userDTO1;
	}

	public void setUserDTO1(UserDTO userDTO1) {
		this.userDTO1 = userDTO1;
	}

	public UserDTO getUserDTO2() {
		return userDTO2;
	}

	public void setUserDTO2(UserDTO userDTO2) {
		this.userDTO2 = userDTO2;
	}

	public AvioFlightDTO getAvioFlightDTO() {
		return avioFlightDTO;
	}

	public void setAvioFlightDTO(AvioFlightDTO avioFlightDTO) {
		this.avioFlightDTO = avioFlightDTO;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

}
