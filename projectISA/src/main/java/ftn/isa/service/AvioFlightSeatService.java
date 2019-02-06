package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ftn.isa.model.AvioFlightSeat;
import ftn.isa.repository.AvioFlightSeatRepository;

@Service
public class AvioFlightSeatService {

	private AvioFlightSeatRepository seatRepository;
	
	public List<AvioFlightSeat> getAllSeats(){
		List<AvioFlightSeat> list = new ArrayList<AvioFlightSeat>();
		seatRepository.findAll().forEach(list::add);
		return list;
	}
	
	public AvioFlightSeat findSeat(Long id) {
		return seatRepository.getOne(id);
	}
}
