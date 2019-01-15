package ftn.isa.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class AvioCompany {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName="id", nullable = false, unique = true)
	private Address address;
	
	private String description;
	private double averageRating;
	
	@ManyToMany
	@JoinTable(name = "destinations",
               joinColumns = @JoinColumn(name="avio_company_id", referencedColumnName="id"), //ovde se pravi medju tabela.
               inverseJoinColumns = @JoinColumn(name="address_id", referencedColumnName="id"))
	private Set<Address> destinations = new HashSet<Address>();
	
	@OneToMany(mappedBy = "avioCompany", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AvioFlight> flights = new ArrayList<>();
	
	@ElementCollection
    @MapKeyColumn(name="user", unique = true, nullable = false)
    @Column(name="rate", nullable = false)
	@CollectionTable(name="avio_company_rates", joinColumns=@JoinColumn(name="id"))
	private Map<Long, Integer> rates = new HashMap<>(); 
	
	public AvioCompany() {}

	public AvioCompany(String name, String description, double averageRating) {
		super();
		this.name = name;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Address> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<Address> destinations) {
		this.destinations = destinations;
	}

	public List<AvioFlight> getFlights() {
		return flights;
	}

	public void setFlights(List<AvioFlight> flights) {
		this.flights = flights;
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

	public Map<Long, Integer> getRates() {
		return rates;
	}

	public void setRates(Map<Long, Integer> rates) {
		this.rates = rates;
	}
	
	
}
