package ftn.isa.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.dto.ReservationDTO;
import ftn.isa.dto.VehicleDTO;
import ftn.isa.dto.VehicleSearchDTO;
import ftn.isa.model.RentCar;
import ftn.isa.model.User;
import ftn.isa.model.Vehicle;
import ftn.isa.model.VehicleReservation;
import ftn.isa.service.RentCarBranchService;
import ftn.isa.service.RentCarService;
import ftn.isa.service.UserService;
import ftn.isa.service.VehicleReservationService;
import ftn.isa.service.VehicleService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "vehicles")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private RentCarBranchService branchService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VehicleReservationService reservationService;
	
	@Autowired
	private RentCarService rentCarService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
		
		List<Vehicle> vehicles = vehicleService.findAll();
		List<VehicleDTO> vehiclesDTO = new ArrayList<>();
		
		for (Vehicle v : vehicles) {
			vehiclesDTO.add(new VehicleDTO(v));
		}
		
		return new ResponseEntity<>(vehiclesDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{idVehicle}", method=RequestMethod.GET)
	public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long idVehicle){
		
		Vehicle vehicle = vehicleService.findOne(idVehicle);
		
		if(vehicle == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new VehicleDTO(vehicle), HttpStatus.OK);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST, consumes="application/json") //bez value mozda
	public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicleDTO){
		
		System.out.println(vehicleDTO.getMark());
		System.out.println(vehicleDTO.getModel());
		System.out.println(vehicleDTO.getName());
		System.out.println(vehicleDTO.getSeatsNumber());
		System.out.println(vehicleDTO.getYearProduced());
		System.out.println(vehicleDTO.getType());
		System.out.println(vehicleDTO.getRentCar().getId());
		
		Vehicle vehicle = new Vehicle();
		
		vehicle.setMark(vehicleDTO.getMark());
		vehicle.setModel(vehicleDTO.getModel());
		vehicle.setName(vehicleDTO.getName());
		vehicle.setSeatsNumber(vehicleDTO.getSeatsNumber());
		vehicle.setType(vehicleDTO.getType());
		vehicle.setYearProduced(vehicleDTO.getYearProduced());
		vehicle.setRentCar(rentCarService.findOne(vehicleDTO.getRentCar().getId()));//ovo
		
		vehicle = vehicleService.save(vehicle);
		
		return new ResponseEntity<>(new VehicleDTO(vehicle), HttpStatus.CREATED);	
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST, consumes="application/json") //bez value mozda
	public ResponseEntity<VehicleDTO> updateRentCar(@RequestBody VehicleDTO vehicleDTO){
		
		Vehicle vehicle = vehicleService.findOne(vehicleDTO.getId()); 
		
		if (vehicle == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		vehicle.setMark(vehicleDTO.getMark());
		vehicle.setModel(vehicleDTO.getModel());
		vehicle.setName(vehicleDTO.getName());
		vehicle.setSeatsNumber(vehicleDTO.getSeatsNumber());
		vehicle.setType(vehicleDTO.getType());
		vehicle.setYearProduced(vehicleDTO.getYearProduced());
		vehicle.setPrice(vehicleDTO.getPrice());
		
		vehicle = vehicleService.update(vehicle);
		
		return new ResponseEntity<>(new VehicleDTO(vehicle), HttpStatus.OK);	
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteVehicle(@PathVariable Long id){
		
		Vehicle vehicle = vehicleService.findOne(id);
		
		if (vehicle != null){
			vehicleService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/reserveVehicle", method=RequestMethod.POST)
	public ResponseEntity<Void> reserveVehicle(@RequestBody ReservationDTO reservationDTO){
		
		User user = (User) userService.loadUserByUsername(reservationDTO.getUsername());
		System.out.println("Nadjen korisnik " + user.getUsername());
		
		VehicleReservation vr = new VehicleReservation();
		
		vr.setBelongsToVehicle(vehicleService.findOne(reservationDTO.getVehicle().getId()));
		vr.setUser(user);
		vr.setStartReservation(Date.valueOf(LocalDate.now()));
		vr.setEndReseravtion(Date.valueOf(LocalDate.now()));
		vr.setTakePlace(branchService.findOne(reservationDTO.getTakePlaceId()));
		vr.setReturnPlace(branchService.findOne(reservationDTO.getReturnPlaceId()));
		vr.calculatePrice();
	
		reservationService.save(vr);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/searchVehicles", method=RequestMethod.POST)
	public ResponseEntity<List<VehicleDTO>> searchVehicles(@RequestBody VehicleSearchDTO params){
		
		RentCar rentCar = rentCarService.findOne(params.getIdRentCar());
		
		List<Vehicle> searchResult = vehicleService.search(rentCar, params);
		List<VehicleDTO> returnList = new ArrayList<>();
		
		for(Vehicle v : searchResult) {
			returnList.add(new VehicleDTO(v));
		}
		
		return new ResponseEntity<>(returnList,HttpStatus.OK);
	}

}
