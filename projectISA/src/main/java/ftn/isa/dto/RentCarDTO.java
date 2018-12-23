package ftn.isa.dto;

import ftn.isa.model.Address;
import ftn.isa.model.RentCar;

public class RentCarDTO {
	
	private Long id;
	private String name;
	private String description;
	private AddressDTO addressDTO;
	
	public RentCarDTO() {}

	public RentCarDTO(Long id, String name, String description, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	    this.addressDTO = new AddressDTO(address);
	}
	
	public RentCarDTO(RentCar car) {
		super();
		this.id = car.getId();
		this.name = car.getName();
		this.description = car.getDescription();
		this.addressDTO = new AddressDTO(car.getAddress());
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}


}
