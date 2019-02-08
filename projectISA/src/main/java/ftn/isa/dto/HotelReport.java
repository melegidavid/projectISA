package ftn.isa.dto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ftn.isa.model.Hotel;
import ftn.isa.model.HotelRoom;
import ftn.isa.model.RoomReservation;

public class HotelReport {
	
	private double avgRating;
	private String avgRatingReport;
	
	@JsonIgnore
	private Hotel hotel;
	private List<String> roomReports = new ArrayList<String>();
	
	private String prihodPerioda;
	
	
	public HotelReport() {}
	
	public HotelReport(Hotel hotel) {
		this.hotel = hotel;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
		this.setAvgRatingReport("Prosecna ocena hotela '" + hotel.getName() + "' je: " + this.getAvgRating());
	}
	
	public List<String> getRoomReports() {
		return roomReports;
	}

	public void setRoomReports(List<String> roomReports) {
		this.roomReports = roomReports;
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

	public void generateRoomReports() {
		
		for(HotelRoom r : hotel.getRooms()) {
			
		     String report = "";
		     
			  double sum = 0;
			  double count = 0;
				
			  List<RoomReservation> reservations = r.getReseravations();
				
			  for(RoomReservation vr : reservations) {
				if(vr.getRoomRating() > 0) {
					sum += vr.getRoomRating();
					count++;
				}
			  }
				
			  if(count == 0) {
			    report = "Soba broj " + r.getNumber() + " jos uvek nije ocenjena.";
			  } else {
				double result = sum/count;
				DecimalFormat df = new DecimalFormat("#.##");      
				result = Double.valueOf(df.format(result));
				report = "Prosecna ocena sobe broj " + r.getNumber() + " je " + result;
			  }
				
			  this.roomReports.add(report);
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

		for(HotelRoom v : this.hotel.getRooms()) {
			   
		       for(RoomReservation vr : v.getReseravations()) {	
		    	   for(Date d : datesInRange) {
		    		   if(d.after(vr.getStartReservation()) && d.before(vr.getEndReservation()) || d.equals(vr.getStartReservation()) || d.equals(vr.getEndReservation())) {
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
