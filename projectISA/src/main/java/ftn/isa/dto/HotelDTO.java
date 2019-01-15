package ftn.isa.dto;

import ftn.isa.model.Address;
import ftn.isa.model.Hotel;

public class HotelDTO {

	private Long id;
	private String name;
	private AddressDTO addressDTO;
	private String description;
	
	public HotelDTO() {
		
	}
	
	public HotelDTO(Hotel hotel) {
		id = hotel.getId();
		name = hotel.getName();
		addressDTO = new AddressDTO(hotel.getAddress());
		description = hotel.getDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
