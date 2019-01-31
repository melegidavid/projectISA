package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.dto.VehicleSearchDTO;
import ftn.isa.model.RentCar;
import ftn.isa.model.Vehicle;
import ftn.isa.model.VehicleReservation;
import ftn.isa.repository.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	
	public Vehicle findOne(Long id) {
		return vehicleRepository.getOne(id);
	}
	
	public List<Vehicle> findAll() {
		List<Vehicle> list = new ArrayList<Vehicle>();
		vehicleRepository.findAll().forEach(list::add);
		return list;
	}
	
	public Vehicle save(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}
	
	public Vehicle update(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}
	
	public void remove(Long id) {
		vehicleRepository.deleteById(id);
	}
	
	public List<Vehicle> search(RentCar rentCar, VehicleSearchDTO params) {
		
		List<Vehicle> allVehicles = rentCar.getVehicles();
		List<Vehicle> searchResult = new ArrayList<>();
		
		for(Vehicle v : allVehicles) {
			
			if(v.getType() != params.getVehicleType()) {
				continue;
			}
			
			if(v.getSeatsNumber() < params.getNumberOfPassengers()) {
				continue; 
			}
			
			
			boolean toAdd = true;
		    for(VehicleReservation res : v.getReseravations()) {
		    	if(params.getReturnDate().after(res.getStartReservation()) && params.getReturnDate().before(res.getEndReseravtion())) {
		    		toAdd = false;
		    	}
		    	
		    	if(params.getTakeDate().after(res.getStartReservation()) && params.getTakeDate().before(res.getEndReseravtion())) {
		    		toAdd = false;
		    	}
		    	
		    	if(res.getStartReservation().after(params.getTakeDate()) && res.getEndReseravtion().before(params.getReturnDate())) {
		    		toAdd = false;
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
