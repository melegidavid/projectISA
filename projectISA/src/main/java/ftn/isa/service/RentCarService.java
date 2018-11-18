package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.RentCar;
import ftn.isa.model.User;
import ftn.isa.repository.RentCarRepository;

@Service
public class RentCarService {

	@Autowired
	private RentCarRepository rentCarRepository;
	
	public List<RentCar> getAllRentCars() {
		List<RentCar> list = new ArrayList<RentCar>();
		rentCarRepository.findAll().forEach(list::add);
		return list;
	}
	
	public void addRentCar(RentCar rentCar) {
		rentCarRepository.save(rentCar);
	}
}
