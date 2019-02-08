package ftn.isa.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.dto.DateRange;
import ftn.isa.dto.RentCarSearchDTO;
import ftn.isa.model.RentCar;
import ftn.isa.model.RentCarBranch;
import ftn.isa.model.RentCarReport;
import ftn.isa.model.Vehicle;
import ftn.isa.model.VehicleReservation;
import ftn.isa.repository.RentCarRepository;

@Service
public class RentCarService {

	@Autowired
	private RentCarRepository rentCarRepository;
	
	
	
	public RentCar findOne(Long id) {
		return rentCarRepository.getOne(id);
	}
	
	public List<RentCar> findAll() {
		List<RentCar> list = new ArrayList<RentCar>();
		rentCarRepository.findAll().forEach(list::add);
		return list;
	}
	
	public RentCar save(RentCar rentCar) {
		return rentCarRepository.save(rentCar);
	}
	
	public RentCar update(RentCar rentCar) {
		return rentCarRepository.save(rentCar);
	}
	
	public void remove(Long id) {
		rentCarRepository.deleteById(id);
	}
	
	public double getAvgRating(Long id) {
		
		RentCar car = this.findOne(id);
		
		double sum = 0;
		double count = 0;
		
		List<Vehicle> vehicles = car.getVehicles();
		
		List<VehicleReservation> reservations = new ArrayList<>();
		
		for(Vehicle v : vehicles) {
			reservations.addAll(v.getReseravations());
		}
		
		for(VehicleReservation vr : reservations) {
			if(vr.getRentCarRating() > 0) {
				sum += vr.getRentCarRating();
				count++;
			}
		}
		
		if(count == 0) {
			return 0;
		}
		
		
		double result = sum/count;
		DecimalFormat df = new DecimalFormat("#.##");      
		result = Double.valueOf(df.format(result));

		return result;
	}
	
	public RentCarReport generateReport(Long id, DateRange dateRange) {
		
		RentCar rentCar = this.findOne(id);
		RentCarReport report = new RentCarReport(rentCar);
		
		report.setAvgRating(this.getAvgRating(id));
		report.generateVehicleReports();
		report.generatePrihod(dateRange);
		
		return report;
	}

	
	
	public List<RentCar> search(RentCarSearchDTO params, AddressService addressService) {
		
		List<RentCar> allRentCars = this.findAll();
		List<RentCar> searchResult = new ArrayList<>();
		
		for(RentCar r : allRentCars) {
			
		   //ako treba da se filtrira po imenu i uneta vrednost nije sadrzana 
		   //u imenu rentcara nemoj nastaviti poredjenje
		   if(!params.getName().equals("")) {
			   if(!r.getName().contains(params.getName())) {
				   continue;
			   }
		   }
		   
		   List<RentCarBranch> branches = r.getBranches();
		   boolean ispravan = false;
		   
		   //poredi za svaki brench da li sadrzi unetu adresu
		   for(RentCarBranch br : branches) {
			   if(br.getAddress().getCountry().contains(params.getAddress().getCountry())
			      && br.getAddress().getCity().contains(params.getAddress().getCity())
			      && br.getAddress().getStreet().contains(params.getAddress().getStreet())) {
                   if(br.getAddress().getNumber() == 0 || br.getAddress().getNumber() == params.getAddress().getNumber()) {
                	   ispravan = true;   
                   }
			   }
		   }
		   
		   //poredi da li se mozda glavni rentca nalazi na unetoj adresi
		   if(r.getAddress().getCountry().contains(params.getAddress().getCountry())
			   && r.getAddress().getCity().contains(params.getAddress().getCity())
			   && r.getAddress().getStreet().contains(params.getAddress().getStreet())) {
	               if(params.getAddress().getNumber() == 0 || r.getAddress().getNumber() == params.getAddress().getNumber()) {
	                   ispravan = true;   
	               }
	       }
		   
		   //ako se bran neko nalazi dodaj u suprotnom nemoj nastaviti poredjenje
		   if(!ispravan) {
			   continue;
		   }
		   
		   
		   
		   
		   //proci za svako vozilo svaku rezervciju i videti da li je slobodan u intervalu
		   if(params.getStartDate() != null && params.getEndDate() != null) {
			  
	
			    List<Date> datesInRange = new ArrayList<>();
			    Calendar calendar = new GregorianCalendar();
			    calendar.setTime(params.getStartDate());
			     
			    Calendar endCalendar = new GregorianCalendar();
			    endCalendar.setTime(params.getEndDate());
			 
			    do {
			        Date result = calendar.getTime();
			        datesInRange.add(result);
			        calendar.add(Calendar.DATE, 1);
			    } while (calendar.before(endCalendar));
				
				boolean toAdd = false;
				
				System.out.println("broj dana za proveru" + datesInRange.size());
				
				for (Date date : datesInRange) {
				
				  
				  
				  for(Vehicle v : r.getVehicles()) {
					  
					  boolean available = true;  
					  
					  for(VehicleReservation vr : v.getReseravations() ) {
						  
						  if(date.after(vr.getStartReservation()) && date.before(vr.getEndReseravtion())) {
							  available = false;
						  }
						  
					  }
					  
					  if(available == true) {
						  toAdd = true;
						  break;
					  }  
				  }
				  
				  if(toAdd == true) {
					  break;
				  }
				
				}

				
				if(!toAdd) {
					continue;
				}
				
			   
		   }
		
		  
		   
		   searchResult.add(r);
		}
		
		
		
		return searchResult;
	}
	
}