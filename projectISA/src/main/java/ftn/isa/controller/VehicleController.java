package ftn.isa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.dto.VehicleDTO;
import ftn.isa.model.RentCarBranch;
import ftn.isa.model.Vehicle;
import ftn.isa.service.RentCarBranchService;
import ftn.isa.service.VehicleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "vehicles")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private RentCarBranchService branchService;
	
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
		
		Vehicle vehicle = new Vehicle();
		
		RentCarBranch rentCarBranch = branchService.findOne(vehicleDTO.getRentCarBranch().getId());
		RentCarBranch returnPlace = branchService.findOne(vehicleDTO.getReturnPlace().getId());
		
		
		if(rentCarBranch == null || returnPlace == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		vehicle.setRentCarBranch(rentCarBranch);
		vehicle.setReturnPlace(returnPlace);
		vehicle.setFree(vehicleDTO.isFree());
		vehicle.setMark(vehicleDTO.getMark());
		vehicle.setModel(vehicleDTO.getModel());
		vehicle.setName(vehicleDTO.getName());
		vehicle.setSeatsNumber(vehicleDTO.getSeatsNumber());
		vehicle.setType(vehicleDTO.getType());
		vehicle.setYearProduced(vehicleDTO.getYearProduced());
		
		vehicle = vehicleService.save(vehicle);
		
		return new ResponseEntity<>(new VehicleDTO(vehicle), HttpStatus.CREATED);	
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT, consumes="application/json") //bez value mozda
	public ResponseEntity<VehicleDTO> updateRentCar(@RequestBody VehicleDTO vehicleDTO){
		
		Vehicle vehicle = vehicleService.findOne(vehicleDTO.getId()); 
		
		if (vehicle == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		vehicle.setFree(vehicleDTO.isFree());
		vehicle.setMark(vehicleDTO.getMark());
		vehicle.setModel(vehicleDTO.getModel());
		vehicle.setName(vehicleDTO.getName());
		vehicle.setSeatsNumber(vehicleDTO.getSeatsNumber());
		vehicle.setType(vehicleDTO.getType());
		vehicle.setYearProduced(vehicleDTO.getYearProduced());
		
		vehicle = vehicleService.update(vehicle);
		
		return new ResponseEntity<>(new VehicleDTO(vehicle), HttpStatus.OK);	
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRentCar(@PathVariable Long id){
		
		Vehicle vehicle = vehicleService.findOne(id);
		
		if (vehicle != null){
			vehicleService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
