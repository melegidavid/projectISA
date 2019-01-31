package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.VehicleReservation;
import ftn.isa.repository.VehicleReservationRepositorty;

@Service
public class VehicleReservationService {

	@Autowired
	private VehicleReservationRepositorty vehicleReservationRepository;
	
	
	public VehicleReservation findOne(Long id) {
		return vehicleReservationRepository.getOne(id);
	}
	
	public List<VehicleReservation> findAll() {
		List<VehicleReservation> list = new ArrayList<VehicleReservation>();
		vehicleReservationRepository.findAll().forEach(list::add);
		return list;
	}
	
	public VehicleReservation save(VehicleReservation vehicle) {
		return vehicleReservationRepository.save(vehicle);
	}
	
	public VehicleReservation update(VehicleReservation vehicle) {
		return vehicleReservationRepository.save(vehicle);
	}
	
	public void remove(Long id) {
		vehicleReservationRepository.deleteById(id);
	}
	
	
	
}
