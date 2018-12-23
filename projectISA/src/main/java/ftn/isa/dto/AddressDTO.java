package ftn.isa.dto;

import ftn.isa.model.Address;

public class AddressDTO {

	private Long id;
	private String country;
	private String city;
	private int postalCode;
	private String street;
	private int number;
	
	public AddressDTO() {}
	
	public AddressDTO(Address address) {
		this.id = address.getId();
		this.country = address.getCountry();
		this.city = address.getCity();
		this.postalCode = address.getPostalCode();
		this.street = address.getStreet();
		this.number = address.getNumber();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
}
