package ftn.isa.dto;

import java.util.Date;

public class DateRange {

	private Date startDate;
	private Date endDate;
	
	public DateRange() {}
	
	public DateRange(Date start, Date end) {
		this.startDate = start;
		this.endDate = end;
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
	
	
	
}
