package ftn.isa.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.dto.VehicleSearchDTO;
import ftn.isa.model.RentCar;
import ftn.isa.model.Vehicle;
import ftn.isa.model.VehicleReservation;
import ftn.isa.repository.VehicleRepository;

@Service
@Transactional(readOnly = true)
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	
	public Vehicle findOne(Long id) {
		return vehicleRepository.getOne(id);
	}
	
	public List<Vehicle> findAll() {
		System.out.println("usao u servis");
		List<Vehicle> list = vehicleRepository.findAll();
		List<Vehicle> result = new ArrayList<Vehicle>();
		
		for(Vehicle v : list) {
			if(!v.isDeleted()) {
				result.add(v);
				System.out.println("#############");
			}
		}
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public Vehicle save(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
	public Vehicle update(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
	public void remove(Long id) {
		Vehicle v = this.findOne(id);
		
		List<VehicleReservation> reservations = v.getReseravations();
		boolean reserved = false;
		Date nowDate = new Date();
		for(VehicleReservation reservation : reservations) {
			if((nowDate.after(reservation.getStartReservation()) 
					&& nowDate.before(reservation.getEndReseravtion())) ||
					nowDate.toString().equals(reservation.getStartReservation()) ||
					nowDate.toString().equals(reservation.getEndReseravtion())) {
				reserved = true;
			}
		}
		
		if(reserved) {
			v.setDeleted(true);
			this.update(v);	
		}
		
	}
	
	public double getAvgRating(Long id) {
		
		Vehicle v = this.findOne(id);
		
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
			return 0;
		}
		
		double result = sum/count;
		DecimalFormat df = new DecimalFormat("#.##");      
		result = Double.valueOf(df.format(result));

		return result;
	}
	
	public List<Vehicle> search(RentCar rentCar, VehicleSearchDTO params) {
		
		List<Vehicle> allVehicles = rentCar.getVehicles();
		List<Vehicle> searchResult = new ArrayList<>();
		
		
		
		for(Vehicle v : allVehicles) {
			
			if(!params.getVehicleType().equals("")) {
				if(!v.getType().toString().equals(params.getVehicleType())) {
					continue;
				}	
			}
			
			
			if(params.getNumberOfPassengers() != 0) {
				if(v.getSeatsNumber() < params.getNumberOfPassengers()) {
					continue; 
				}
			}
		
			
			boolean toAdd = true;
			
		    for(VehicleReservation res : v.getReseravations()) {
		    	
		    	if(params.getReturnDate() != null) {
		    		if(params.getReturnDate().after(res.getStartReservation()) && params.getReturnDate().before(res.getEndReseravtion())) {
			    		toAdd = false;
			    	}
		    	}
		    	
		    	if(params.getTakeDate() != null) {
		    		if(params.getTakeDate().after(res.getStartReservation()) && params.getTakeDate().before(res.getEndReseravtion())) {
			    		toAdd = false;
			    	}
		    	}
		    	
		    	
		    	if(params.getReturnDate() != null && params.getTakeDate() != null ) {
		    		if(res.getStartReservation().after(params.getTakeDate()) && res.getEndReseravtion().before(params.getReturnDate())) {
		    			toAdd = false;
		    		}		    		
		    	}
		    }
		    
		    if(!toAdd) {
		    	continue;
		    }
		    
		    //opciono cenovni rang
		    
		    searchResult.add(v);
		}

		
		return searchResult;
	}
	
}
