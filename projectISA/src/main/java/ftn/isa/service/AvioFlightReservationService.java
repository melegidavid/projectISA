package ftn.isa.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.model.AvioFlightReservation;
import ftn.isa.repository.AvioFlightReservationRepository;

@Service
@Transactional(readOnly = true)
public class AvioFlightReservationService {

	@Autowired
	private AvioFlightReservationRepository flightReservationRepository;

	public List<AvioFlightReservation> getAllReservation(){
		List<AvioFlightReservation> list = new ArrayList<AvioFlightReservation>();
		list = flightReservationRepository.findAll();
		List<AvioFlightReservation> result = new ArrayList<AvioFlightReservation>();
		for (AvioFlightReservation reservation : list) {
			if (reservation.isDeleted() != true) {
				result.add(reservation);
			}
		}
		return result;
	}
	
	public AvioFlightReservation findReservation(Long id) {
		return flightReservationRepository.getOne(id);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AvioFlightReservation saveReservation(AvioFlightReservation res) {
		return flightReservationRepository.save(res);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
	public AvioFlightReservation updateReservation(AvioFlightReservation res) {
		return flightReservationRepository.save(res);		
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void removeReservation(Long id) {
		flightReservationRepository.getOne(id).setDeleted(true);
		flightReservationRepository.save(flightReservationRepository.getOne(id));
	}
}
