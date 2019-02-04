package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.AvioFlight;
import ftn.isa.repository.AvioFlightRepository;

@Service
public class AvioFlightService {

	@Autowired
	private AvioFlightRepository avioFlightRepository;
	
	//get all
	public List<AvioFlight> getAllAvioFlights(){
		List<AvioFlight> list = new ArrayList<AvioFlight>();
		avioFlightRepository.findAll().forEach(list::add);
		return list;
	}
	
	//get one
	public AvioFlight findAvioFlight(Long id) {
		return avioFlightRepository.getOne(id);
	}
	
	//save 
	public AvioFlight saveAvioFlight(AvioFlight avioFlight) {
		return avioFlightRepository.save(avioFlight);
	}
	
	//update
	public AvioFlight updateAvioFlight(AvioFlight avioFlight) {
		return avioFlightRepository.save(avioFlight);
	}
	
	//delete
	public void removeAvioFlight(Long id) {
		avioFlightRepository.getOne(id).setDeleted(true);
		avioFlightRepository.save(avioFlightRepository.getOne(id));
	}
	
}
