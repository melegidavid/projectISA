package ftn.isa.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.search.AddressTerm;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.dto.AddressDTO;
import ftn.isa.dto.AvioCompanyDTO;
import ftn.isa.dto.AvioFlightDTO;
import ftn.isa.dto.AvioFlightSearchDTO;
import ftn.isa.model.Address;
import ftn.isa.model.AvioCompany;
import ftn.isa.model.AvioFlight;
import ftn.isa.service.AddressService;
import ftn.isa.service.AvioCompanyService;
import ftn.isa.service.AvioFlightService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/avio_companies")
public class AvioCompanyController {

	@Autowired
	private AvioCompanyService avioCompanyService;

	@Autowired
	private AvioFlightService avioFlightService;

	@Autowired
	private AddressService addressService;

	// kontroler avio kompanije

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AvioCompanyDTO>> getAvioCompanies() {
		List<AvioCompany> avioCompanies = avioCompanyService.getAllAvioCompanies();
		List<AvioCompanyDTO> avioCompmaniesDTO = new ArrayList<AvioCompanyDTO>();
		for (AvioCompany avioCompany : avioCompanies) {
			avioCompmaniesDTO.add(new AvioCompanyDTO(avioCompany));
		}
		return new ResponseEntity<>(avioCompmaniesDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AvioCompanyDTO> getAvioCompany(@PathVariable("id") Long id) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(id);

		if (avioCompany == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (avioCompany.isDeleted() == true) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new AvioCompanyDTO(avioCompany), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AvioCompanyDTO> addAvioCompany(@RequestBody AvioCompanyDTO avioCompanyDTO) {
		AvioCompany avioCompany = new AvioCompany();
//		Address address = addressService.findOne(avioCompanyDTO.getAddress().getId());
//
//		if (address == null) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
		Address address = new Address();
		address.setCountry(avioCompanyDTO.getAddress().getCountry());
		address.setCity(avioCompanyDTO.getAddress().getCity());
		address.setPostalCode(avioCompanyDTO.getAddress().getPostalCode());
		address.setStreet(avioCompanyDTO.getAddress().getStreet());
		address.setNumber(avioCompanyDTO.getAddress().getNumber());

		address = addressService.save(address);

		avioCompany.setName(avioCompanyDTO.getName());
		avioCompany.setDescription(avioCompanyDTO.getDescription());
		avioCompany.setAddress(address);
		avioCompany.setAverageRating(avioCompanyDTO.getAverageRating());
		avioCompany.setDeleted(false);
		avioCompany = avioCompanyService.saveAvioCompany(avioCompany);

		return new ResponseEntity<>(new AvioCompanyDTO(avioCompany), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AvioCompanyDTO> updateAvioCompnay(@RequestBody AvioCompanyDTO avioCompanyDTO) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(avioCompanyDTO.getId());
		if (avioCompany == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (avioCompany.isDeleted() == true) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		avioCompany.setName(avioCompanyDTO.getName());
		avioCompany.setDescription(avioCompanyDTO.getDescription());
		avioCompany.setAverageRating(avioCompanyDTO.getAverageRating());

		avioCompany = avioCompanyService.saveAvioCompany(avioCompany);

		return new ResponseEntity<>(new AvioCompanyDTO(avioCompany), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeAvioCompany(@PathVariable("id") Long id) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(id);
		if (avioCompany != null && avioCompany.isDeleted() == false) {
			avioCompanyService.removeAvioComapny(avioCompany.getId());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// kontroler avio letova

	@RequestMapping(value = "/{id}/flights", method = RequestMethod.GET)
	public ResponseEntity<List<AvioFlightDTO>> getFlightsOfAviocompany(@PathVariable("id") Long id) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(id);

		if (avioCompany == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (avioCompany.isDeleted() == true) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Set<Address> destinations = avioCompany.getDestinations();
		List<AvioFlight> flights = avioCompany.getFlights();

		List<AvioFlightDTO> flightsDTO = new ArrayList<AvioFlightDTO>();

		if (!destinations.isEmpty() && !flights.isEmpty()) {
			for (AvioFlight avioFlight : flights) {
				if (destinations.contains(avioFlight.getStartLocation())
						&& destinations.contains(avioFlight.getEndLocation())) {
					if (!avioFlight.isDeleted()) {
						flightsDTO.add(new AvioFlightDTO(avioFlight));
					}
				}
			}
		}
		return new ResponseEntity<>(flightsDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/flights/{flightId}", method = RequestMethod.GET)
	public ResponseEntity<AvioFlightDTO> getOneFlightOfAvioCompany(@PathVariable("id") Long id,
			@PathVariable("flightId") Long flightId) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(id);

		if (avioCompany == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (avioCompany.isDeleted() == true) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<AvioFlight> flights = avioCompany.getFlights();

		if (flights.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		AvioFlight tempFlight = avioFlightService.findAvioFlight(flightId);
		if (tempFlight == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (tempFlight.isDeleted() == true) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (flights.contains(tempFlight)) {
			AvioFlightDTO avioFlightDTO = new AvioFlightDTO(tempFlight);
			return new ResponseEntity<>(avioFlightDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}/flights", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AvioFlightDTO> addFlightToAvioCompany(@PathVariable("id") Long id,
			@RequestBody AvioFlightDTO avioFlightDTO) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(id);

		if (avioCompany == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<AvioFlight> flights = new ArrayList<AvioFlight>();

		AvioFlight avioFlight = new AvioFlight();
		if (avioCompany.getFlights() != null) {
			flights = avioCompany.getFlights();
			Address startLocation = addressService.findOne(avioFlightDTO.getStartLocation().getId());
			Address endLocation = addressService.findOne(avioFlightDTO.getEndLocation().getId());
			avioFlight.setStartLocation(startLocation);
			avioFlight.setEndLocation(endLocation);
			avioFlight.setDateTimeStart(avioFlightDTO.getDateTimeStart());
			avioFlight.setDateTimeFinish(avioFlightDTO.getDateTimeFinish());
			avioFlight.setFlightDuration(avioFlightDTO.getFlightDuration());
			avioFlight.setFlightDistance(avioFlightDTO.getFlightDistance());
			avioFlight.setPrice(avioFlightDTO.getPrice());
			avioFlight.setDeleted(false);
			avioFlight.setAvioCompany(avioCompany);
			avioFlight = avioFlightService.saveAvioFlight(avioFlight);

			flights.add(avioFlight);
			avioCompany.setFlights(flights);
			avioCompany = avioCompanyService.saveAvioCompany(avioCompany);

		}

		return new ResponseEntity<>(new AvioFlightDTO(avioFlight), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{id}/flights/{flightId}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AvioFlightDTO> updateFlightOfAvioCompany(@PathVariable("id") Long id,
			@PathVariable("flightId") Long flightId, @RequestBody AvioFlightDTO avioFlightDTO) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(id);

		if (avioCompany == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (avioCompany.isDeleted() == true) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<AvioFlight> flights = avioCompany.getFlights();
		AvioFlight tempFlight = avioFlightService.findAvioFlight(flightId);

		if (tempFlight == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (tempFlight.isDeleted() == true) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (flights.contains(tempFlight) && !tempFlight.isDeleted()) {
			Address startLocation = addressService.findOne(avioFlightDTO.getStartLocation().getId());
			Address endLocation = addressService.findOne(avioFlightDTO.getEndLocation().getId());

			tempFlight.setStartLocation(startLocation);
			tempFlight.setEndLocation(endLocation);
			tempFlight.setDateTimeStart(avioFlightDTO.getDateTimeStart());
			tempFlight.setDateTimeFinish(avioFlightDTO.getDateTimeFinish());
			tempFlight.setFlightDuration(avioFlightDTO.getFlightDuration());
			tempFlight.setFlightDistance(avioFlightDTO.getFlightDistance());
			tempFlight.setPrice(avioFlightDTO.getPrice());
			tempFlight.setDeleted(avioFlightDTO.isDeleted());
			tempFlight = avioFlightService.updateAvioFlight(tempFlight);

			return new ResponseEntity<>(new AvioFlightDTO(tempFlight), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}/flights/{flightId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeFlightOfAvioCompany(@PathVariable("id") Long id,
			@PathVariable("flightId") Long flightId) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(id);

		if (avioCompany == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (avioCompany.isDeleted() == true) {
			System.out.println("OVDE PUKO");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<AvioFlight> flights = avioCompany.getFlights();
		AvioFlight tempFlight = avioFlightService.findAvioFlight(flightId);

		if (tempFlight == null || tempFlight.isDeleted() == true) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			if (flights.contains(tempFlight)) {
				avioFlightService.removeAvioFlight(flightId);
				flights.remove(tempFlight);
				avioCompany.setFlights(flights);
				avioCompany = avioCompanyService.saveAvioCompany(avioCompany);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	}

	@RequestMapping(value = "/{id}/destinations", method = RequestMethod.GET)
	public ResponseEntity<List<AddressDTO>> getDestinationsOfAvioCompany(@PathVariable("id") Long id) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(id);
		Set<Address> destinations = avioCompany.getDestinations();

		List<AddressDTO> destinationsDTO = new ArrayList<AddressDTO>();

		if (!destinations.isEmpty()) {
			for (Address destinatnion : destinations) {
				destinationsDTO.add(new AddressDTO(destinatnion));
			}
		}
		return new ResponseEntity<>(destinationsDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}/destination", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AddressDTO> addDestination(@PathVariable("id") Long id,
			@RequestBody AddressDTO destinationDTO) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(id);
		if (avioCompany == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Set<Address> destinations = new HashSet<Address>();

		Address tempAddress = new Address();

		if (avioCompany.getDestinations() != null) {
			destinations = avioCompany.getDestinations();
			
			tempAddress.setCountry(destinationDTO.getCountry());
			tempAddress.setCity(destinationDTO.getCity());
			tempAddress.setPostalCode(destinationDTO.getPostalCode());
			tempAddress.setStreet(destinationDTO.getStreet());
			tempAddress.setNumber(destinationDTO.getNumber());
			
			tempAddress = addressService.save(tempAddress);
			destinations.add(tempAddress);
			avioCompany.setDestinations(destinations);
			avioCompany = avioCompanyService.saveAvioCompany(avioCompany);
			
		}

		return new ResponseEntity<>(new AddressDTO(tempAddress), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/removeDestination/{idDestination}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeDestinationOfAvioCompany(@PathVariable("id") Long id,
			@PathVariable("idDestination") Long idDestination) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(id);
		Set<Address> destinations = avioCompany.getDestinations();
		if (destinations.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Address destination = addressService.findOne(idDestination);
		if (destinations.contains(destination)) {
			List<AvioFlight> flights = avioCompany.getFlights();
			if (!flights.isEmpty()) {
				List<AvioFlight> tempFlights = avioCompany.getFlights();
				for (AvioFlight flight : tempFlights) {
					if ((flight.getStartLocation().getId().equals(destination.getId()))
							|| flight.getEndLocation().getId().equals(destination.getId())) {
						System.out.println("USO OVDE");
						avioFlightService.removeAvioFlight(flight.getId());
						// avioCompany.getFlights().remove(flight);
					}
				}
			}
			avioCompany.getDestinations().remove(destination);
			avioCompany = avioCompanyService.saveAvioCompany(avioCompany);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//get all destinations
	@RequestMapping(value="/destinations", method=RequestMethod.GET)
	public ResponseEntity<List<AddressDTO>> getAllDestinations(){
		List<AvioCompany> avioCompanies = avioCompanyService.getAllAvioCompanies();
		
		List<Address> destinations = new ArrayList<Address>();
		
		if(avioCompanies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		//metod za dodavanje svih destinacija bez ponavljanja
		for(AvioCompany avioCompany : avioCompanies) {
			Set<Address> tempDestinations = avioCompany.getDestinations();
			for(Address tempDestination : tempDestinations) {
				if(!destinations.contains(tempDestination)) {
					destinations.add(tempDestination);
				}
			}
		}
		
		List<AddressDTO> destinationsDTO = new ArrayList<AddressDTO>();
		
		for(Address address : destinations) {
			destinationsDTO.add(new AddressDTO(address));
		}
		
		return new ResponseEntity<>(destinationsDTO, HttpStatus.OK);
	}
	
	
	//search
	@RequestMapping(value="/search", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<List<AvioFlightDTO>> searchFlights(@RequestBody AvioFlightSearchDTO searchFlightsDTO){
		List<AvioFlight> searchResult = avioFlightService.searchFlights(searchFlightsDTO, this.addressService);
		List<AvioFlightDTO> avioFlightsDTO = new ArrayList<AvioFlightDTO>();
		
		if(!searchResult.isEmpty()) {
			for(AvioFlight avioFlight : searchResult) {
				avioFlightsDTO.add(new AvioFlightDTO(avioFlight));
			}
			
		}
		return new ResponseEntity<>(avioFlightsDTO, HttpStatus.OK);
	}

}
