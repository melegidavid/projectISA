package ftn.isa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

@Entity
public class AvioFullFlight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String dateTimeStart;
	private String dateTimeFinish;
	private double fullFlightDuration;
	private double fullFlightDistance;
	private double fullPrice;
	
	@Version
	private Long version;
	
	@ManyToMany
	@JoinTable(name = "locationOfTransfers",
               joinColumns = @JoinColumn(name="full_flight_id", referencedColumnName="id"), 
               inverseJoinColumns = @JoinColumn(name="flight_id", referencedColumnName="id"))
	private List<AvioFlight> locationOfTransfers = new ArrayList<>();
	public AvioFullFlight() {}

	public AvioFullFlight(String dateTimeStart, String dateTimeFinish, double fullFlightDuration,
			double fullFlightDistance, double fullPrice) {
		super();
		this.dateTimeStart = dateTimeStart;
		this.dateTimeFinish = dateTimeFinish;
		this.fullFlightDuration = fullFlightDuration;
		this.fullFlightDistance = fullFlightDistance;
		this.fullPrice = fullPrice;
	
	}
	
	//vraca avio kompanije svih letova 
	public List<AvioCompany> getAvioCompaniesOfFlights() {
		List<AvioCompany> temp = new ArrayList<AvioCompany>();
		for(AvioFlight flight : locationOfTransfers) {
			if(!temp.contains(flight.getAvioCompany())) {
				temp.add(flight.getAvioCompany());
			}
		}
		return temp;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(String dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public String getDateTimeFinish() {
		return dateTimeFinish;
	}

	public void setDateTimeFinish(String dateTimeFinish) {
		this.dateTimeFinish = dateTimeFinish;
	}

	public double getFullFlightDuration() {
		return fullFlightDuration;
	}

	public void setFullFlightDuration(double fullFlightDuration) {
		this.fullFlightDuration = fullFlightDuration;
	}

	public double getFullFlightDistance() {
		return fullFlightDistance;
	}

	public void setFullFlightDistance(double fullFlightDistance) {
		this.fullFlightDistance = fullFlightDistance;
	}

	public double getFullPrice() {
		return fullPrice;
	}

	public void setFullPrice(double fullPrice) {
		this.fullPrice = fullPrice;
	}

	public List<AvioFlight> getLocationOfTransfers() {
		return locationOfTransfers;
	}

	public void setLocationOfTransfers(List<AvioFlight> locationOfTransfers) {
		this.locationOfTransfers = locationOfTransfers;
	}
	
	
	
}
