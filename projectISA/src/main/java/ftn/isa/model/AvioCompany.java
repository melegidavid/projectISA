package ftn.isa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AvioCompany {

	@Id	
	private Long id;
	private String name;
	private String address;
	private String description;
	private double averageRating;
	
	public AvioCompany() {}

	public AvioCompany(Long id, String name, String address, String description, double averageRating) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.averageRating = averageRating;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
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
