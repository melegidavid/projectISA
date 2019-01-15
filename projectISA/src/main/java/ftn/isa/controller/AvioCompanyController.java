package ftn.isa.controller;

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

import ftn.isa.dto.AvioCompanyDTO;
import ftn.isa.dto.AvioFlightDTO;
import ftn.isa.model.Address;
import ftn.isa.model.AvioCompany;
import ftn.isa.model.AvioFlight;
import ftn.isa.service.AddressService;
import ftn.isa.service.AvioCompanyService;
import ftn.isa.service.AvioFlightService;

@RestController
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
		AvioCompany avioCopmany = avioCompanyService.findAvioCompany(id);

		if (avioCopmany == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new AvioCompanyDTO(avioCopmany), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AvioCompanyDTO> addAvioCompany(@RequestBody AvioCompanyDTO avioCompanyDTO) {
		AvioCompany avioCompany = new AvioCompany();
		Address address = addressService.findOne(avioCompanyDTO.getAddress().getId());

		if (address == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		avioCompany.setName(avioCompanyDTO.getName());
		avioCompany.setDescription(avioCompanyDTO.getDescription());
		avioCompany.setAddress(address);
		avioCompany.setAverageRating(avioCompanyDTO.getAverageRating());

		avioCompany = avioCompanyService.saveAvioCompany(avioCompany);

		return new ResponseEntity<>(new AvioCompanyDTO(avioCompany), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<AvioCompanyDTO> updateAvioCompnay(@RequestBody AvioCompanyDTO avioCompanyDTO) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(avioCompanyDTO.getId());
		if (avioCompany == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
		if (avioCompany != null) {
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
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<AvioFlight> flights = avioCompany.getFlights();

		if (flights.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<AvioFlightDTO> flightsDTO = new ArrayList<AvioFlightDTO>();

		for (AvioFlight avioFlight : flights) {
			flightsDTO.add(new AvioFlightDTO(avioFlight));
		}

		return new ResponseEntity<>(flightsDTO, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/{id}/flights/{flightId}", method = RequestMethod.GET)
	public ResponseEntity<AvioFlightDTO> getOneFlightOfAvioCompany(@PathVariable("id") Long id,
			@PathVariable("flightId") Long flightId) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(id);

		if (avioCompany == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<AvioFlight> flights = avioCompany.getFlights();

		if (flights.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		AvioFlight tempFlight = avioFlightService.findAvioFlight(flightId);
		if (tempFlight == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (flights.contains(tempFlight)) {
			AvioFlightDTO avioFlightDTO = new AvioFlightDTO(tempFlight);
			return new ResponseEntity<>(avioFlightDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	
	@RequestMapping(value = "/{id}/flights", method = RequestMethod.POST, consumes = "applicationjson")
	public ResponseEntity<AvioFlightDTO> addFlightToAvioCompany(@PathVariable("id") Long id,
			@RequestBody AvioFlightDTO avioFlightDTO) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(id);

		if (avioCompany == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<AvioFlight> flights = avioCompany.getFlights();

		AvioFlight avioFlight = new AvioFlight();
		avioFlight.setDateTimeStart(avioFlightDTO.getDateTimeStart());
		avioFlight.setDateTimeFinish(avioFlightDTO.getDateTimeFinish());
		avioFlight.setFlightDuration(avioFlightDTO.getFlightDuration());
		avioFlight.setFlightDistance(avioFlightDTO.getFlightDistance());
		avioFlight.setPrice(avioFlightDTO.getPrice());

		avioFlight = avioFlightService.saveAvioFlight(avioFlight);

		flights.add(avioFlight);
		avioCompany.setFlights(flights);
		avioCompany = avioCompanyService.saveAvioCompany(avioCompany);

		return new ResponseEntity<>(new AvioFlightDTO(avioFlight), HttpStatus.CREATED);

	}

	
	@RequestMapping(value = "/{id}/flights/{flightId}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<AvioFlightDTO> updateFlightOfAvioCompany(@PathVariable("id") Long id,
			@PathVariable("flightId") Long flightId, @RequestBody AvioFlightDTO avioFlightDTO) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(id);

		if (avioCompany == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<AvioFlight> flights = avioCompany.getFlights();
		AvioFlight tempFlight = avioFlightService.findAvioFlight(flightId);

		if (tempFlight == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (flights.contains(tempFlight)) {
			flights.remove(tempFlight);

			tempFlight.setDateTimeStart(avioFlightDTO.getDateTimeStart());
			tempFlight.setDateTimeFinish(avioFlightDTO.getDateTimeFinish());
			tempFlight.setFlightDuration(avioFlightDTO.getFlightDuration());
			tempFlight.setFlightDistance(avioFlightDTO.getFlightDistance());
			tempFlight.setPrice(avioFlightDTO.getPrice());

			tempFlight = avioFlightService.saveAvioFlight(tempFlight);

			flights.add(tempFlight);
			avioCompany.setFlights(flights);
			avioCompany = avioCompanyService.saveAvioCompany(avioCompany);

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
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<AvioFlight> flights = avioCompany.getFlights();
		AvioFlight tempFlight = avioFlightService.findAvioFlight(flightId);

		if (tempFlight == null) {
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

}
