package ftn.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.model.RentCar;
import ftn.isa.service.RentCarService;

@RestController
public class RentCarController {

	@Autowired
	private RentCarService rentCarService;
	
	@RequestMapping(value="/rentCars",method = RequestMethod.GET)
	public List<RentCar> getRentCars() {
		return rentCarService.getAllRentCars();
	}
	
	@RequestMapping(value="/rentCars",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addUser(@RequestBody RentCar rentCar) {
		rentCarService.addRentCar(rentCar);
	}
}
