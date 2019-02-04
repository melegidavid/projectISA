package ftn.isa.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ftn.isa.model.Address;
import ftn.isa.model.AvioCompany;

public class AvioCompanyDTO {

	private Long id;
	private String name;
	private AddressDTO address;
	private String description;
	private double averageRating;
	private Set<AddressDTO> destinations;
	private boolean isDeleted;

	public AvioCompanyDTO() {

	}

	public AvioCompanyDTO(AvioCompany avioCompany) {
		this.destinations = new HashSet<AddressDTO>();
		this.id = avioCompany.getId();
		this.name = avioCompany.getName();
		this.address = new AddressDTO(avioCompany.getAddress());
		this.description = avioCompany.getDescription();
		this.averageRating = avioCompany.getAverageRating();
		this.isDeleted = avioCompany.isDeleted();
		for (Address address : avioCompany.getDestinations()) {
			this.destinations.add(new AddressDTO(address));
		}
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

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public Set<AddressDTO> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<AddressDTO> destinations) {
		this.destinations = destinations;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
