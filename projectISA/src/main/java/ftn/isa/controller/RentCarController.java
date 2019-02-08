package ftn.isa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.dto.DateRange;
import ftn.isa.dto.RentCarDTO;
import ftn.isa.dto.RentCarMenuItemDTO;
import ftn.isa.dto.RentCarSearchDTO;
import ftn.isa.dto.VehicleDTO;
import ftn.isa.model.Address;
import ftn.isa.model.RentCar;
import ftn.isa.model.RentCarMenuItem;
import ftn.isa.model.RentCarReport;
import ftn.isa.model.Vehicle;
import ftn.isa.service.AddressService;
import ftn.isa.service.RentCarMenuItemService;
import ftn.isa.service.RentCarService;
import ftn.isa.service.VehicleService;

@RestController
@RequestMapping(value="/rent_a_cars")
public class RentCarController {

	@Autowired
	private RentCarService rentCarService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private RentCarMenuItemService menuItemService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<RentCarDTO>> getAllRentCars() {
		
		List<RentCar> rentCars = rentCarService.findAll();
		List<RentCarDTO> rentCarsDTO = new ArrayList<>();
		
		for (RentCar r : rentCars) {
			rentCarsDTO.add(new RentCarDTO(r));
		}
		
		return new ResponseEntity<>(rentCarsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/search", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<List<RentCarDTO>> searchRentCars(@RequestBody RentCarSearchDTO params) {
		
		List<RentCar> searchResult = rentCarService.search(params, this.addressService);
		List<RentCarDTO> rentCarsDTO = new ArrayList<>();
		
		if(!searchResult.isEmpty()) {
		
			for (RentCar r : searchResult) {
				rentCarsDTO.add(new RentCarDTO(r));
			}	
		}
		
		return new ResponseEntity<>(rentCarsDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<RentCarDTO> getRentCar(@PathVariable Long id){
		
		RentCar rentCar = rentCarService.findOne(id);
		
		if(rentCar == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new RentCarDTO(rentCar), HttpStatus.OK);
	}

	
	@RequestMapping(value="/save", method=RequestMethod.POST, consumes="application/json") //bez value mozda
	public ResponseEntity<RentCarDTO> saveRentCar(@RequestBody RentCarDTO rentCarDTO){
		
		RentCar rentCar = new RentCar();
		
		Address address = new Address();
		address.setCountry(rentCarDTO.getAddressDTO().getCountry());
		address.setCity(rentCarDTO.getAddressDTO().getCity());
		address.setPostalCode(rentCarDTO.getAddressDTO().getPostalCode());
		address.setStreet(rentCarDTO.getAddressDTO().getStreet());
		address.setNumber(rentCarDTO.getAddressDTO().getNumber());
		
		address = addressService.save(address);
		
		//rentCar.setId(rentCarDTO.getId());
		rentCar.setName(rentCarDTO.getName());
		rentCar.setDescription(rentCarDTO.getDescription());
		rentCar.setAddress(address);
		
		rentCar = rentCarService.save(rentCar);
		System.out.println("auto dodat");
		return new ResponseEntity<>(new RentCarDTO(rentCar), HttpStatus.CREATED);	
	}
	
	
	@RequestMapping(value="/update", method=RequestMethod.POST, consumes="application/json") //bez value mozda
	public ResponseEntity<RentCarDTO> updateRentCar(@RequestBody RentCarDTO rentCarDTO){
		
		RentCar rentCar = rentCarService.findOne(rentCarDTO.getId()); 
		
		if (rentCar == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		rentCar.setName(rentCarDTO.getName());
		rentCar.setDescription(rentCarDTO.getDescription());
		
		rentCar = rentCarService.update(rentCar);
		
		return new ResponseEntity<>(new RentCarDTO(rentCar), HttpStatus.OK);	
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRentCar(@PathVariable Long id){
		
		RentCar rentCar = rentCarService.findOne(id);
		
		if (rentCar != null){
			rentCarService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}/avgRentCarRating", method=RequestMethod.GET)
	public Double getRentCarAvgRating(@PathVariable Long id){
		
		return rentCarService.getAvgRating(id);
	}
	
	@RequestMapping(value="/{id}/avgVehicleRating", method=RequestMethod.GET)
	public Double getVehicleAvgRating(@PathVariable Long id){
		
		return vehicleService.getAvgRating(id);
	}
	
	

	@RequestMapping(value="/{idRentCar}/menu", method=RequestMethod.GET)
	public ResponseEntity<List<RentCarMenuItemDTO>> getRentCarMenu(@PathVariable Long idRentCar){
		
		RentCar rentCar = rentCarService.findOne(idRentCar);
		
		if(rentCar == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<RentCarMenuItem> menu = rentCar.getRentCarMenu();
		
		if(menu.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		List<RentCarMenuItemDTO> menuDTO = new ArrayList<RentCarMenuItemDTO>();
		
		for(RentCarMenuItem r : menu) {
			menuDTO.add(new RentCarMenuItemDTO(r));
		}
		
		return new ResponseEntity<>(menuDTO, HttpStatus.OK);
	}
	
	//vraca odredjenu stavku menija za odredjeni rent a car servis
	@RequestMapping(value="/{idRentCar}/menu/{idMenuItem}", method=RequestMethod.GET)
	public ResponseEntity<RentCarMenuItemDTO> getRentCarMenuItem(@PathVariable Long idRentCar, @PathVariable Long idMenuItem){
		
		RentCarMenuItem item = menuItemService.findOne(idMenuItem);
		
		if(item == null) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
			
		RentCarMenuItemDTO menuItemDTO = new RentCarMenuItemDTO(item);		
		
		return new ResponseEntity<>(menuItemDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{idRentCar}/saveMenuItem", method=RequestMethod.POST, consumes="application/json") //bez value mozda
	public ResponseEntity<RentCarMenuItemDTO> saveRentCarMenuItem(@PathVariable Long idRentCar , @RequestBody RentCarMenuItemDTO rentCarMenuItemDTO){
		
		RentCarMenuItem rentCarMenuItem = new RentCarMenuItem();
		rentCarMenuItem.setPrice(rentCarMenuItemDTO.getPrice());
		rentCarMenuItem.setDescription(rentCarMenuItemDTO.getDescription());
		rentCarMenuItem.setServiceName(rentCarMenuItemDTO.getServiceName());
		rentCarMenuItem.setRentCar(rentCarService.findOne(idRentCar));
		
		menuItemService.save(rentCarMenuItem);
		
		return new ResponseEntity<>(new RentCarMenuItemDTO(rentCarMenuItem), HttpStatus.CREATED);	
	}
	
	
	@RequestMapping(value="/{idRentCar}/updateMenuItem", method=RequestMethod.PUT, consumes="application/json") //bez value mozda
	public ResponseEntity<RentCarMenuItemDTO> updateRentCarMenuItem(@RequestBody RentCarMenuItemDTO itemDTO){
		
		RentCarMenuItem menuItem = menuItemService.findOne(itemDTO.getId()); 
		
		if (menuItem == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		menuItem.setPrice(itemDTO.getPrice());
		menuItem.setDescription(itemDTO.getDescription());
		menuItem.setServiceName(itemDTO.getServiceName());
		
		menuItem = menuItemService.update(menuItem);
		
		return new ResponseEntity<>(new RentCarMenuItemDTO(menuItem), HttpStatus.OK);	
	}
	
	
	@RequestMapping(value="/{idRentCar}/{idMenuItem}/deleteMenuItem", method=RequestMethod.DELETE) //bez value mozda
	public ResponseEntity<Void> deleteRentCarMenuItem(@PathVariable Long idMenuItem){
		
		RentCarMenuItem menuItem = menuItemService.findOne(idMenuItem);

		if(menuItem == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		menuItemService.remove(idMenuItem);
		
		return new ResponseEntity<>(HttpStatus.OK);	
	}

	@RequestMapping(value="/{idRentCar}/vehicles", method=RequestMethod.GET) 
	public ResponseEntity<List<VehicleDTO>> getRentCarVehicles(@PathVariable("idRentCar") Long id) {
		RentCar rentCar = rentCarService.findOne(id);
		
		if(rentCar == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Vehicle> vehicles = rentCar.getVehicles();
		
		System.out.println(vehicles.size());
		
		if(vehicles.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<VehicleDTO> vehiclesDTO = new ArrayList<VehicleDTO>();
		for(Vehicle v: vehicles) {
			vehiclesDTO.add(new VehicleDTO(v));
		}
		
		return new ResponseEntity<>(vehiclesDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{idRentCar}/vehicles", method=RequestMethod.POST) 
	public ResponseEntity<VehicleDTO> saveRentCarVehicle(@PathVariable Long idRentCar , @RequestBody VehicleDTO vehicleDTO) {
		Vehicle vehicle = new Vehicle();
		
		RentCar rentCar = rentCarService.findOne(vehicleDTO.getRentCar().getId());
		
		vehicle.setRentCar(rentCar);
		vehicle.setMark(vehicleDTO.getMark());
		vehicle.setModel(vehicleDTO.getModel());
		vehicle.setName(vehicleDTO.getName());
		vehicle.setSeatsNumber(vehicleDTO.getSeatsNumber());
		vehicle.setType(vehicleDTO.getType());
		vehicle.setYearProduced(vehicleDTO.getYearProduced());
		vehicle.setPrice(vehicleDTO.getPrice());
		
		vehicle = vehicleService.save(vehicle);
		
		return new ResponseEntity<>(new VehicleDTO(vehicle), HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="/{idRentCar}/generate", method=RequestMethod.POST) 
	public ResponseEntity<RentCarReport> generateReport(@PathVariable Long idRentCar, @RequestBody DateRange dateRange) {
		
		RentCarReport rcr = rentCarService.generateReport(idRentCar, dateRange);
		
		return new ResponseEntity<>(rcr, HttpStatus.CREATED);
	}
	
}
