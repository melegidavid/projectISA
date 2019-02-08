package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.model.VehicleReservation;
import ftn.isa.repository.VehicleReservationRepositorty;

@Service
@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = false)
	public VehicleReservation save(VehicleReservation vehicle) {
		return vehicleReservationRepository.save(vehicle);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
	public VehicleReservation update(VehicleReservation vehicle) {
		return vehicleReservationRepository.save(vehicle);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		vehicleReservationRepository.deleteById(id);
	}
	
	
	
}
