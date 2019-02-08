package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.model.AvioFullFlight;
import ftn.isa.repository.AvioFullFlightRepository;

@Service
@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AvioFullFlight saveAvioFullFlight(AvioFullFlight avioFullFlight) {
		return avioFullFlightRepository.save(avioFullFlight);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
	public AvioFullFlight updateAvioFullFlight(AvioFullFlight avioFullFlight) {
		return avioFullFlightRepository.save(avioFullFlight);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void removeAvioFullFlight(Long id) {
		avioFullFlightRepository.deleteById(id);
	}
}
