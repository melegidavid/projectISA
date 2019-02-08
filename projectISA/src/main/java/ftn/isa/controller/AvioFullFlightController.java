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

import ftn.isa.dto.AvioCompanyDTO;
import ftn.isa.dto.AvioFlightDTO;
import ftn.isa.dto.AvioFullFlightDTO;
import ftn.isa.model.AvioCompany;
import ftn.isa.model.AvioFlight;
import ftn.isa.model.AvioFullFlight;
import ftn.isa.service.AvioFlightService;
import ftn.isa.service.AvioFullFlightService;

@RestController
@RequestMapping(value = "/flights_with_transfers")
public class AvioFullFlightController {

	@Autowired
	private AvioFullFlightService avioFullFlightService;

	@Autowired
	private AvioFlightService avioFlightService;

	// avio full flight controller

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AvioFullFlightDTO>> getAvioFullFlights() {
		List<AvioFullFlight> avioFullFlights = avioFullFlightService.getAllAvioFullFlights();
		List<AvioFullFlightDTO> avioFullFlightsDTO = new ArrayList<AvioFullFlightDTO>();
		for (AvioFullFlight avioFullFlight : avioFullFlights) {
			avioFullFlightsDTO.add(new AvioFullFlightDTO(avioFullFlight));
		}
		return new ResponseEntity<>(avioFullFlightsDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AvioFullFlightDTO> getAvioFullFlight(@PathVariable("id") Long id) {
		AvioFullFlight avioFullFlight = avioFullFlightService.findAvioFullFlight(id);
		if (avioFullFlight == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new AvioFullFlightDTO(avioFullFlight), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('AVIO_COMPANY_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AvioFullFlightDTO> addAvioFullFlight(@RequestBody AvioFullFlightDTO avioFullFlightDTO) {
		AvioFullFlight avioFullFlight = new AvioFullFlight();

		avioFullFlight.setDateTimeStart(avioFullFlightDTO.getDateTimeStart());
		avioFullFlight.setDateTimeFinish(avioFullFlightDTO.getDateTimeFinish());
		avioFullFlight.setFullFlightDistance(avioFullFlightDTO.getFullFlightDistance());
		avioFullFlight.setFullFlightDuration(avioFullFlightDTO.getFullFlightDuration());
		avioFullFlight.setFullPrice(avioFullFlightDTO.getFullPrice());

		avioFullFlight = avioFullFlightService.saveAvioFullFlight(avioFullFlight);
		return new ResponseEntity<>(new AvioFullFlightDTO(avioFullFlight), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('AVIO_COMPANY_ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<AvioFullFlightDTO> updateAvioFullFlight(@RequestBody AvioFullFlightDTO avioFullFlightDTO) {
		AvioFullFlight avioFullFlight = avioFullFlightService.findAvioFullFlight(avioFullFlightDTO.getId());
		if (avioFullFlight == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		avioFullFlight.setDateTimeStart(avioFullFlightDTO.getDateTimeStart());
		avioFullFlight.setDateTimeFinish(avioFullFlightDTO.getDateTimeFinish());
		avioFullFlight.setFullFlightDistance(avioFullFlightDTO.getFullFlightDistance());
		avioFullFlight.setFullFlightDuration(avioFullFlightDTO.getFullFlightDuration());
		avioFullFlight.setFullPrice(avioFullFlightDTO.getFullPrice());

		avioFullFlight = avioFullFlightService.saveAvioFullFlight(avioFullFlight);

		return new ResponseEntity<>(new AvioFullFlightDTO(avioFullFlight), HttpStatus.OK);

	}

	@PreAuthorize("hasRole('AVIO_COMPANY_ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeAvioFullFlight(@PathVariable("id") Long id) {
		AvioFullFlight avioFullFlight = avioFullFlightService.findAvioFullFlight(id);
		if (avioFullFlight != null) {
			avioFullFlightService.removeAvioFullFlight(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// avio flight of full flight controller
	@RequestMapping(value = "/{id}/flights", method = RequestMethod.GET)
	public ResponseEntity<List<AvioFlightDTO>> getFlightsOfFullFlight(@PathVariable("id") Long id) {
		AvioFullFlight avioFullFlight = avioFullFlightService.findAvioFullFlight(id);

		if (avioFullFlight == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<AvioFlight> flights = avioFullFlight.getLocationOfTransfers();

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
	public ResponseEntity<AvioFlightDTO> getOneFlightOfFullFlight(@PathVariable("id") Long id,
			@PathVariable("flightId") Long flightId) {
		AvioFullFlight avioFullFlight = avioFullFlightService.findAvioFullFlight(id);

		if (avioFullFlight == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<AvioFlight> flights = avioFullFlight.getLocationOfTransfers();

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

	@PreAuthorize("hasRole('AVIO_COMPANY_ADMIN')")
	@RequestMapping(value = "/{id}/flights", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AvioFlightDTO> addFlightToFullFlight(@PathVariable("id") Long id,
			@RequestBody AvioFlightDTO avioFlightDTO) {
		AvioFullFlight avioFullFlight = avioFullFlightService.findAvioFullFlight(id);

		if (avioFullFlight == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<AvioFlight> flights = avioFullFlight.getLocationOfTransfers();

		AvioFlight avioFlight = new AvioFlight();
		avioFlight.setDateTimeStart(avioFlightDTO.getDateTimeStart());
		avioFlight.setDateTimeFinish(avioFlightDTO.getDateTimeFinish());
		avioFlight.setFlightDuration(avioFlightDTO.getFlightDuration());
		avioFlight.setFlightDistance(avioFlightDTO.getFlightDistance());
		avioFlight.setPrice(avioFlightDTO.getPrice());

		avioFlight = avioFlightService.saveAvioFlight(avioFlight);

		flights.add(avioFlight);
		avioFullFlight.setLocationOfTransfers(flights);
		avioFullFlight = avioFullFlightService.saveAvioFullFlight(avioFullFlight);

		return new ResponseEntity<>(new AvioFlightDTO(avioFlight), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('AVIO_COMPANY_ADMIN')")
	@RequestMapping(value = "/{id}/flights/{flightId}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<AvioFlightDTO> updateFlightOfFullFlight(@PathVariable("id") Long id,
			@PathVariable("flightId") Long flightId, @RequestBody AvioFlightDTO avioFlightDTO) {
		AvioFullFlight avioFullFlight = avioFullFlightService.findAvioFullFlight(id);
		if (avioFullFlight == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<AvioFlight> flights = avioFullFlight.getLocationOfTransfers();

		AvioFlight avioFlight = avioFlightService.findAvioFlight(flightId);

		if (avioFlight == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (flights.contains(avioFlight)) {
			flights.remove(avioFlight);

			avioFlight.setDateTimeStart(avioFlightDTO.getDateTimeStart());
			avioFlight.setDateTimeFinish(avioFlightDTO.getDateTimeFinish());
			avioFlight.setFlightDuration(avioFlightDTO.getFlightDuration());
			avioFlight.setFlightDistance(avioFlightDTO.getFlightDistance());
			avioFlight.setPrice(avioFlightDTO.getPrice());

			avioFlight = avioFlightService.saveAvioFlight(avioFlight);

			flights.add(avioFlight);
			avioFullFlight.setLocationOfTransfers(flights);

			avioFullFlight = avioFullFlightService.saveAvioFullFlight(avioFullFlight);

			return new ResponseEntity<>(new AvioFlightDTO(avioFlight), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('AVIO_COMPANY_ADMIN')")
	@RequestMapping(value = "/{id}/flights/{flightId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeFlightOfFullFlight(@PathVariable("id") Long id,
			@PathVariable("flightId") Long flightId) {
		AvioFullFlight avioFullFlight = avioFullFlightService.findAvioFullFlight(id);

		if (avioFullFlight == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<AvioFlight> flights = avioFullFlight.getLocationOfTransfers();

		AvioFlight avioFlight = avioFlightService.findAvioFlight(flightId);

		if (avioFlight == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			if (flights.contains(avioFlight)) {
				avioFlightService.removeAvioFlight(flightId);
				flights.remove(avioFlight);
				avioFullFlight.setLocationOfTransfers(flights);
				avioFullFlight = avioFullFlightService.saveAvioFullFlight(avioFullFlight);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	}

	@RequestMapping(value = "/{id}/avio_companies", method = RequestMethod.GET)
	public ResponseEntity<List<AvioCompanyDTO>> getAvioCompaniesOfFullFlith(@PathVariable("id") Long id) {
		AvioFullFlight avioFullFlight = avioFullFlightService.findAvioFullFlight(id);

		if (avioFullFlight == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<AvioCompany> avioCompanies = avioFullFlight.getAvioCompaniesOfFlights();
		if (avioCompanies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<AvioCompanyDTO> avioCompaniesDTO = new ArrayList<AvioCompanyDTO>();
		for (AvioCompany avioCompany : avioCompanies) {
			avioCompaniesDTO.add(new AvioCompanyDTO(avioCompany));
		}
		return new ResponseEntity<>(avioCompaniesDTO, HttpStatus.OK);
	}

}
