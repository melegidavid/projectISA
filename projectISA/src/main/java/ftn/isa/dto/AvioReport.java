package ftn.isa.dto;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ftn.isa.model.AvioCompany;
import ftn.isa.model.AvioFlight;
import ftn.isa.model.AvioFlightReservation;

public class AvioReport {
	
	private double avgRating;
	private String avgRatingReport;
	
	@JsonIgnore
	private AvioCompany company;
	private List<String> flightReports = new ArrayList<String>();
	
	private String prihodPerioda;
	
	
	public AvioReport() {}
	
	public AvioReport(AvioCompany rc) {
		this.company = rc;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
		this.setAvgRatingReport("Prosecna ocena avio kompanije'" + company.getName() + "' je: " + this.getAvgRating());
	}
	
	public List<String> getFlightReports() {
		return flightReports;
	}

	public void setFlightReports(List<String> flightReports) {
		this.flightReports = flightReports;
	}

	public String getPrihodPerioda() {
		return prihodPerioda;
	}

	public void setPrihodPerioda(String prihodPerioda) {
		this.prihodPerioda = prihodPerioda;
	}

	public String getAvgRatingReport() {
		return avgRatingReport;
	}

	public void setAvgRatingReport(String avgRatingReport) {
		this.avgRatingReport = avgRatingReport;
	}

	public void generateFlightReports() {
		
		for(AvioFlight a : company.getFlights()) {
			
		     String report = "";
		     
			  double sum = 0;
			  double count = 0;
				
			  List<AvioFlightReservation> reservations = a.getReseravations();
				
			  for(AvioFlightReservation vr : reservations) {
				if(vr.getRatingFlight() > 0) {
					sum += vr.getRatingFlight();
					count++;
				}
			  }
			  
				
			  if(count == 0) {
			    report = "Let " + a.getStartLocation().getCity() + " [" + a.getStartLocation().getCountry()  + "]  - " + a.getEndLocation().getCity()  + " [" + a.getEndLocation().getCountry()  + "] (" + a.getDateTimeStart() + "h) jos uvek nije ocenjen.";
			  } else {
				double result = sum/count;
				DecimalFormat df = new DecimalFormat("#.##");      
				result = Double.valueOf(df.format(result));
				report = "Prosecna ocena leta " + a.getStartLocation().getCity() + " [" + a.getStartLocation().getCountry()  + "]  - " + a.getEndLocation().getCity()  + " [" + a.getEndLocation().getCountry()  + "] (" + a.getDateTimeStart() + "h) je " + result;
			  }
				
			  this.flightReports.add(report);
		}
	}
	
	public void generatePrihod(DateRange dateRange) {
		
		
		List<Date> datesInRange = new ArrayList<>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(dateRange.getStartDate());
	     
	    Calendar endCalendar = new GregorianCalendar();
	    endCalendar.setTime(dateRange.getEndDate());
	 
	    do {
	        Date result = calendar.getTime();
	        datesInRange.add(result);
	        calendar.add(Calendar.DATE, 1);
	    } while (calendar.before(endCalendar));
		
	    System.out.println(datesInRange.size());
		int prihod = 0;

		for(AvioFlight v : this.company.getFlights()) {
			   
		       for(AvioFlightReservation vr : v.getReseravations()) {	
		    	   for(Date date : datesInRange) {
		    		   LocalDateTime d = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		    		   if(d.isAfter(vr.getAvioFlight().getDateTimeStart()) && d.isBefore(vr.getAvioFlight().getDateTimeFinish()) || d.equals(vr.getAvioFlight().getDateTimeStart()) || d.equals(vr.getAvioFlight().getDateTimeFinish())) {
		    			   prihod += v.getPrice();
		    		   }
		    	   }
		       }
	    }
		
		
		
		String message = "";
		message += "Prihod za period od '" +  dateRange.getStartDate().toString() + "' do '";
		message += dateRange.getEndDate().toString() + "' je : " + prihod + " dinara";
		
	    this.setPrihodPerioda(message);	
	}


}
