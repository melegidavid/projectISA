package ftn.isa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AvioCompany {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String address;
	private String description;
	private double averageRating;
	
	@OneToMany(mappedBy = "avioCompany", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AvioFlight> flights;
	
	public AvioCompany() {}

	public AvioCompany(String name, String address, String description, double averageRating) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.averageRating = averageRating;
		this.flights = new ArrayList<>();
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
