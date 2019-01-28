package ftn.isa.dto;

import ftn.isa.model.Address;
import ftn.isa.model.RentCar;
import ftn.isa.model.RentCarBranch;

public class RentCarBranchDTO {

	private Long id;
	private String name;
	private AddressDTO addressDTO;
	private RentCarDTO rentCarDTO;
	
	public RentCarBranchDTO() {}

	public RentCarBranchDTO(Long id, String name, RentCar rentCar, Address address) {
		super();
		this.id = id;
		this.name = name;	
		this.addressDTO = new AddressDTO(address);
		this.rentCarDTO = new RentCarDTO(rentCar);
	}
	
	public RentCarBranchDTO(RentCarBranch branch) {
		this.id = branch.getId();
		this.name = branch.getName();
		this.addressDTO = new AddressDTO(branch.getAddress());
		this.rentCarDTO = new RentCarDTO(branch.getRentCar());
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

	public RentCarDTO getRentCarDTO() {
		return rentCarDTO;
	}

	public void setRentCarDTO(RentCarDTO rentCarDTO) {
		this.rentCarDTO = rentCarDTO;
	}
	
}
