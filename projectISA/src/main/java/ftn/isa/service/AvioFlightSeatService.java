package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.model.AvioFlightSeat;
import ftn.isa.repository.AvioFlightSeatRepository;

@Service
@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = false , propagation = Propagation.REQUIRED)
	public AvioFlightSeat saveSeat(AvioFlightSeat seat) {
		return seatRepository.save(seat);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
	public AvioFlightSeat updateSeat(AvioFlightSeat seat) {
		return seatRepository.save(seat);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void removeSeat(Long id) {
		seatRepository.getOne(id).setDeleted(true);
		seatRepository.save(seatRepository.getOne(id));
	}
}
