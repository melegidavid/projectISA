package ftn.isa.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

@Entity
public class HotelRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String number;
	private boolean free;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Hotel hotel;
	
	@ElementCollection
    @MapKeyColumn(name="user", unique = true, nullable = false)
    @Column(name="rate", nullable = false)
	@CollectionTable(name="hotel_room_rates", joinColumns=@JoinColumn(name="id"))
	private Map<Long, Integer> rates = new HashMap<>(); 
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reservation> reseravations = new ArrayList<>();
	
	public HotelRoom() {
		
	}

	public HotelRoom(String number, boolean free, Hotel hotel) {
		super();
		this.number = number;
		this.free = free;
		this.hotel = hotel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Map<Long, Integer> getRates() {
		return rates;
	}

	public void setRates(Map<Long, Integer> rates) {
		this.rates = rates;
	}

	public List<Reservation> getReseravations() {
		return reseravations;
	}

	public void setReseravations(List<Reservation> reseravations) {
		this.reseravations = reseravations;
	}
	
}
