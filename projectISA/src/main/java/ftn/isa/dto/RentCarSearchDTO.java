package ftn.isa.dto;

import java.util.Date;

public class RentCarSearchDTO {
	
	private String name;
	private Date startDate;
	private Date endDate;
	private AddressDTO address;
	
	public RentCarSearchDTO() {}
	
	public RentCarSearchDTO(String name, Date start, Date end, AddressDTO address) {
		this.name = name;
		this.startDate = start;
		this.endDate = end;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}
	
	

}
