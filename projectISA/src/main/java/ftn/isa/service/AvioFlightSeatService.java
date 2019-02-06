package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.AvioFlightSeat;
import ftn.isa.repository.AvioFlightSeatRepository;

@Service
public class AvioFlightSeatService {

	@Autowired
	private AvioFlightSeatRepository seatRepository;
	
	public List<AvioFlightSeat> getAllSeats(){
		List<AvioFlightSeat> list = new ArrayList<AvioFlightSeat>();
		seatRepository.findAll().forEach(list::add);
		return list;
	}
	
	public AvioFlightSeat findSeat(Long id) {
		return seatRepository.getOne(id);
	}
	
	public AvioFlightSeat saveSeat(AvioFlightSeat seat) {
		return seatRepository.save(seat);
	}
	
	public AvioFlightSeat updateSeat(AvioFlightSeat seat) {
		return seatRepository.save(seat);
	}
	
	public void removeSeat(Long id) {
		seatRepository.getOne(id).setDeleted(true);
		seatRepository.save(seatRepository.getOne(id));
	}
}
