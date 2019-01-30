package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.dto.RentCarSearchDTO;
import ftn.isa.model.RentCar;
import ftn.isa.model.RentCarBranch;
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
	
	public List<RentCar> search(RentCarSearchDTO params, AddressService addressService) {
		
		List<RentCar> allRentCars = this.findAll();
		List<RentCar> searchResult = new ArrayList<>();
		
		for(RentCar r : allRentCars) {
			
		   //ako treba da se filtrira po imenu i uneta vrednost nije sadrzana 
		   //u imenu rentcara ne moj nastaviti poredjenje
		   if(!params.getName().equals("")) {
			   if(!r.getName().contains(params.getName())) {
				   continue;
			   }
		   }
		   
		   List<RentCarBranch> branches = r.getBranches();
		   boolean ispravan = false;
		   
		   //poredi za svaki brench da li sadrzi unetu adresu
		   for(RentCarBranch br : branches) {
			   if(br.getAddress().getCountry().contains(params.getAddress().getCountry())
			      && br.getAddress().getCity().contains(params.getAddress().getCity())
			      && br.getAddress().getStreet().contains(params.getAddress().getStreet())) {
                   if(br.getAddress().getNumber() == 0 || br.getAddress().getNumber() == params.getAddress().getNumber()) {
                	   ispravan = true;   
                   }
			   }
		   }
		   
		   //poredi da li se mozda glavni rentca nalazi na unetoj adresi
		   if(r.getAddress().getCountry().contains(params.getAddress().getCountry())
			   && r.getAddress().getCity().contains(params.getAddress().getCity())
			   && r.getAddress().getStreet().contains(params.getAddress().getStreet())) {
	               if(params.getAddress().getNumber() == 0 || r.getAddress().getNumber() == params.getAddress().getNumber()) {
	                   ispravan = true;   
	               }
	       }
		   
		   //ako se bran neko nalazi dodaj u suprotnom nemoj nastaviti poredjenje
		   if(!ispravan) {
			   continue;
		   }
		   
		   
		   //proci za svako vozilo svaku rezervciju i videti da li je slobodan u intervalu
		   if(params.getStartDate() != null) {
			   
		   }
		
		   if(params.getEndDate() != null) {
			   
		   }
		   
		   searchResult.add(r);
		}
		
		
		
		return searchResult;
	}
	
}