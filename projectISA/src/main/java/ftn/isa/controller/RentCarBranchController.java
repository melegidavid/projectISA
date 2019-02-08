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

import ftn.isa.dto.RentCarBranchDTO;
import ftn.isa.model.Address;
import ftn.isa.model.RentCar;
import ftn.isa.model.RentCarBranch;
import ftn.isa.service.AddressService;
import ftn.isa.service.RentCarBranchService;
import ftn.isa.service.RentCarService;

@RestController
@RequestMapping(value = "rent_car_branches")
public class RentCarBranchController {
	
	@Autowired
	private RentCarBranchService branchService;
	
	@Autowired 
	private AddressService addressService;
	
	@Autowired
	private RentCarService rentCarService;
	
	
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<RentCarBranchDTO>> getAllRentCars() {
		
		List<RentCarBranch> branches = branchService.findAll();
		List<RentCarBranchDTO> branchesDTO = new ArrayList<>();
		
		for (RentCarBranch r : branches) {
			branchesDTO.add(new RentCarBranchDTO(r));
		}
		
		return new ResponseEntity<>(branchesDTO, HttpStatus.OK);
	}
	
	//metoda za SVE brancheve ID rentaCar servisa
	@RequestMapping(value="/branchesOf/{idRentCar}", method=RequestMethod.GET)
	public ResponseEntity<List<RentCarBranchDTO>> getRentCarBranchesOf(@PathVariable Long idRentCar){
		
		RentCar rentCar = rentCarService.findOne(idRentCar);
		
		if(rentCar == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<RentCarBranch> branches = rentCar.getBranches();
		List<RentCarBranchDTO> branchesDTO = new ArrayList<>();
		
		for (RentCarBranch r : branches) {
			branchesDTO.add(new RentCarBranchDTO(r));
		}
		
		return new ResponseEntity<>(branchesDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{idBranch}", method=RequestMethod.GET)
	public ResponseEntity<RentCarBranchDTO> getRentCar(@PathVariable Long idBranch){
		
		RentCarBranch rentCarBranch = branchService.findOne(idBranch);
		
		if(rentCarBranch == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new RentCarBranchDTO(rentCarBranch), HttpStatus.OK);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST, consumes="application/json") //bez value mozda
	public ResponseEntity<RentCarBranchDTO> saveRentCarBranch(@RequestBody RentCarBranchDTO rentCarBranchDTO){
		
		RentCarBranch rentCarBranch = new RentCarBranch();
		
		RentCar rentCar = rentCarService.findOne(rentCarBranchDTO.getRentCarDTO().getId());
			
		Address address = new Address();
		address.setCountry(rentCarBranchDTO.getAddressDTO().getCountry());
		address.setCity(rentCarBranchDTO.getAddressDTO().getCity());
		address.setStreet(rentCarBranchDTO.getAddressDTO().getStreet());
		address.setNumber(rentCarBranchDTO.getAddressDTO().getNumber());
		address.setPostalCode(rentCarBranchDTO.getAddressDTO().getPostalCode());
		addressService.save(address);
				
		rentCarBranch.setName(rentCarBranchDTO.getName());
		rentCarBranch.setAddress(address);
		rentCarBranch.setRentCar(rentCar);
			
		rentCarBranch = branchService.save(rentCarBranch);
		
		return new ResponseEntity<>(new RentCarBranchDTO(rentCarBranch), HttpStatus.CREATED);	
	}
	
	
	@RequestMapping(value="/update", method=RequestMethod.POST, consumes="application/json") //bez value mozda
	public ResponseEntity<RentCarBranchDTO> updateRentCarBranch(@RequestBody RentCarBranchDTO rentCarBranchDTO){
		
		RentCarBranch rentCarBranch = branchService.findOne(rentCarBranchDTO.getId()); 
		
		if (rentCarBranch == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		rentCarBranch.setName(rentCarBranchDTO.getName());
		rentCarBranch.getAddress().setCountry(rentCarBranchDTO.getAddressDTO().getCountry());
		rentCarBranch.getAddress().setCity(rentCarBranchDTO.getAddressDTO().getCity());
		rentCarBranch.getAddress().setStreet(rentCarBranchDTO.getAddressDTO().getStreet());
		rentCarBranch.getAddress().setPostalCode(rentCarBranchDTO.getAddressDTO().getPostalCode());
		rentCarBranch.getAddress().setNumber(rentCarBranchDTO.getAddressDTO().getNumber());
		
		rentCarBranch = branchService.update(rentCarBranch);
		
		return new ResponseEntity<>(new RentCarBranchDTO(rentCarBranch), HttpStatus.OK);	
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRentCarBranch(@PathVariable Long id){
		
		RentCarBranch rentCarBranch = branchService.findOne(id);
		
		if (rentCarBranch != null){
			branchService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
