package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftn.isa.model.Vehicle;
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
	
}
