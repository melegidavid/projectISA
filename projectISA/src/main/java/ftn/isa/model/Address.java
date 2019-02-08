package ftn.isa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Address {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String country;
	private String city;
	private int postalCode;
	private String street;
	private int number;
	
	@Version
	private Long version;
	
	public Address() {
		
	}

	public Address(String country, String city, int postalCode, String street, int number) {
		super();
		this.country = country;
		this.city = city;
		this.postalCode = postalCode;
		this.street = street;
		this.number = number;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
