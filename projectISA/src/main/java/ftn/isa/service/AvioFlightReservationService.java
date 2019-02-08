package ftn.isa.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.AvioFlightReservation;
import ftn.isa.repository.AvioFlightReservationRepository;

@Service
public class AvioFlightReservationService {

	@Autowired
	private AvioFlightReservationRepository flightReservationRepository;

	public List<AvioFlightReservation> getAllReservation(){
		List<AvioFlightReservation> list = new ArrayList<AvioFlightReservation>();
		flightReservationRepository.findAll().forEach(list::add);
		return list;
	}
	
	public AvioFlightReservation findReservation(Long id) {
		return flightReservationRepository.getOne(id);
	}
	
	public AvioFlightReservation saveReservation(AvioFlightReservation res) {
		return flightReservationRepository.save(res);
	}
	
	public AvioFlightReservation updateReservation(AvioFlightReservation res) {
		return flightReservationRepository.save(res);		
	}
	
	
	public void removeReservation(Long id) {
		flightReservationRepository.deleteById(id);
	}
}
