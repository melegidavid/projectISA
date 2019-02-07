package ftn.isa.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ftn.isa.dto.DateRange;

public class RentCarReport {
	
	private double avgRating;
	private String avgRatingReport;
	
	@JsonIgnore
	private RentCar rentCar;
	private List<String> vehicleReports = new ArrayList<String>();
	
	private String prihodPerioda;
	
	
	public RentCarReport() {}
	
	public RentCarReport(RentCar rc) {
		this.rentCar = rc;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
		this.setAvgRatingReport("Prosecna ocena rent-a-car servisa '" + rentCar.getName() + "' je: " + this.getAvgRating());
	}
	
	public List<String> getVehicleReports() {
		return vehicleReports;
	}

	public void setVehicleReports(List<String> vehicleReports) {
		this.vehicleReports = vehicleReports;
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

	public void generateVehicleReports() {
		
		for(Vehicle v : rentCar.getVehicles()) {
			
		     String report = "";
		     
			  double sum = 0;
			  double count = 0;
				
			  List<VehicleReservation> reservations = v.getReseravations();
				
			  for(VehicleReservation vr : reservations) {
				if(vr.getVehicleRating() > 0) {
					sum += vr.getVehicleRating();
					count++;
				}
			  }
				
			  if(count == 0) {
			    report = "Vozilo" + v.getMark() + " " + v.getModel() + " (" + v.getYearProduced() + ") jos uvek nije ocenjeno.";
			  } else {
				double result = sum/count;
				DecimalFormat df = new DecimalFormat("#.##");      
				result = Double.valueOf(df.format(result));
				report = "Prosecna ocena vozila " + v.getMark() + " " + v.getModel() + " (" + v.getYearProduced() + ") je " + result;
			  }
				
			  this.vehicleReports.add(report);
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

		for(Vehicle v : this.rentCar.getVehicles() ) {
			   
		       for(VehicleReservation vr : v.getReseravations()) {	
		    	   for(Date d : datesInRange) {
		    		   if(d.after(vr.getStartReservation()) && d.before(vr.getEndReseravtion()) || d.equals(vr.getStartReservation()) || d.equals(vr.getEndReseravtion())) {
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
