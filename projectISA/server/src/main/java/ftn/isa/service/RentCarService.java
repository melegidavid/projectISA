package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftn.isa.model.RentCar;
import ftn.isa.repository.RentCarRepository;

@Service
public class RentCarService {

	@Autowired
	private RentCarRepository rentCarRepository;
	
	
	
	public RentCar findOne(Long id) {
		return rentCarRepository.getOne(id);
	}
	
	public List<RentCar> findAll() {
		List<RentCar> list = new ArrayList<RentCar>();
		rentCarRepository.findAll().forEach(list::add);
		return list;
	}
	
	public RentCar save(RentCar rentCar) {
		return rentCarRepository.save(rentCar);
	}
	
	public RentCar update(RentCar rentCar) {
		return rentCarRepository.save(rentCar);
	}
	
	public void remove(Long id) {
		rentCarRepository.deleteById(id);
	}
	
}