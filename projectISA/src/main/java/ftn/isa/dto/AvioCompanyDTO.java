package ftn.isa.dto;

import ftn.isa.model.AvioCompany;

public class AvioCompanyDTO {

	private Long id;
	private String name;
	private AddressDTO address;
	private String description;
	private double averageRating;
	
	public AvioCompanyDTO() {
		
	}
	
	public AvioCompanyDTO(AvioCompany avioCompany) {
		this.id = avioCompany.getId();
		this.name = avioCompany.getName();
		this.address = new AddressDTO(avioCompany.getAddress());
		this.description = avioCompany.getDescription();
		this.averageRating = avioCompany.getAverageRating();
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
}
