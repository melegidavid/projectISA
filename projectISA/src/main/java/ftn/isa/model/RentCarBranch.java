package ftn.isa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Where;

@Entity
@Where(clause="deleted=0")
public class RentCarBranch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private boolean deleted;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName="id", nullable = false, unique = true)
	private Address address;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private RentCar rentCar;
	
	
	public RentCarBranch() {
		
	}

	public RentCarBranch(String name) {
		super();
		this.name = name;
		this.deleted = false;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public RentCar getRentCar() {
		return rentCar;
	}

	public void setRentCar(RentCar rentCar) {
		this.rentCar = rentCar;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
