package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.AvioFullFlight;
import ftn.isa.repository.AvioFullFlightRepository;

@Service
public class AvioFullFlightService {

	@Autowired
	private AvioFullFlightRepository avioFullFlightRepository;
	
	public List<AvioFullFlight> getAllAvioFullFlights(){
		List<AvioFullFlight> list = new ArrayList<AvioFullFlight>();
		avioFullFlightRepository.findAll().forEach(list::add);
		return list;
	}
	
	public AvioFullFlight findAvioFullFlight(Long id) {
		return avioFullFlightRepository.getOne(id);
	}
	
	public AvioFullFlight saveAvioFullFlight(AvioFullFlight avioFullFlight) {
		return avioFullFlightRepository.save(avioFullFlight);
	}
	
	public AvioFullFlight updateAvioFullFlight(AvioFullFlight avioFullFlight) {
		return avioFullFlightRepository.save(avioFullFlight);
	}
	
	public void removeAvioFullFlight(Long id) {
		avioFullFlightRepository.deleteById(id);
	}
}
