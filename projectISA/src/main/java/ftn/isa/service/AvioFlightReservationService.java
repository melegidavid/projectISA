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
		list = flightReservationRepository.findAll();
		List<AvioFlightReservation> result = new ArrayList<AvioFlightReservation>();
		for(AvioFlightReservation res : list) {
			if(res.isDeleted() != true) {
				result.add(res);
			}
		}
		return result;
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
		flightReservationRepository.getOne(id).setDeleted(true);
		flightReservationRepository.save(flightReservationRepository.getOne(id));
	}
}
