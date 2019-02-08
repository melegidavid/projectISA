package ftn.isa.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.dto.AddressDTO;
import ftn.isa.dto.AvioCompanyDTO;
import ftn.isa.dto.AvioFlightDTO;
import ftn.isa.dto.AvioFlightReservationDTO;
import ftn.isa.dto.AvioFlightSearchDTO;
import ftn.isa.dto.AvioFlightSeatDTO;
import ftn.isa.dto.AvioReport;
import ftn.isa.dto.DateRange;
import ftn.isa.model.Address;
import ftn.isa.model.AvioCompany;
import ftn.isa.model.AvioFlight;
import ftn.isa.model.AvioFlightReservation;
import ftn.isa.model.AvioFlightSeat;
import ftn.isa.model.InviteForFlight;
import ftn.isa.model.User;
import ftn.isa.service.AddressService;
import ftn.isa.service.AvioCompanyService;
import ftn.isa.service.AvioFlightReservationService;
import ftn.isa.service.AvioFlightSeatService;
import ftn.isa.service.AvioFlightService;
import ftn.isa.service.InviteForFlightService;
import ftn.isa.service.UserService;

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

	@Autowired
	private AvioFlightSeatService seatService;

	@Autowired
	private UserService userService;

	@Autowired
	private AvioFlightReservationService reservationService;

	@Autowired
	private InviteForFlightService inviteService;

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
			avioFlight.setEconomyClassSeats(avioFlightDTO.getEconomyClassSeats());
			avioFlight.setBusinessClassSeats(avioFlightDTO.getBusinessClassSeats());
			avioFlight.setFirstClassSeats(avioFlightDTO.getFirstClassSeats());

			List<AvioFlightSeat> tempEconomy = new ArrayList<AvioFlightSeat>();
			List<AvioFlightSeat> tempBusiness = new ArrayList<AvioFlightSeat>();
			List<AvioFlightSeat> tempFirst = new ArrayList<AvioFlightSeat>();

			// economy
			for (int i = 0; i < avioFlight.getEconomyClassSeats(); i++) {
				AvioFlightSeat tempEconomySeat = new AvioFlightSeat();
				tempEconomySeat.setFree(true);
				tempEconomySeat.setNumber(i + 1);
				tempEconomySeat.setClassOfSeat("economy");
				tempEconomy.add(tempEconomySeat);
			}
			// business
			for (int i = 0; i < avioFlight.getBusinessClassSeats(); i++) {
				AvioFlightSeat tempBusinessSeat = new AvioFlightSeat();
				tempBusinessSeat.setFree(true);
				tempBusinessSeat.setNumber(i + 1);
				tempBusinessSeat.setClassOfSeat("business");
				tempBusiness.add(tempBusinessSeat);
			}
			// first
			for (int i = 0; i < avioFlight.getFirstClassSeats(); i++) {
				AvioFlightSeat tempFirstSeat = new AvioFlightSeat();
				tempFirstSeat.setFree(true);
				tempFirstSeat.setNumber(i + 1);
				tempFirstSeat.setClassOfSeat("first");

				tempFirst.add(tempFirstSeat);
			}

			List<AvioFlightSeat> flightSeats = new ArrayList<AvioFlightSeat>();

			flightSeats = new ArrayList<AvioFlightSeat>();
			if (!tempEconomy.isEmpty()) {
				for (AvioFlightSeat seatEconomy : tempEconomy) {
					flightSeats.add(seatEconomy);
				}
			}
			if (!tempBusiness.isEmpty()) {
				for (AvioFlightSeat seatBusiness : tempBusiness) {
					flightSeats.add(seatBusiness);
				}
			}
			if (!tempFirst.isEmpty()) {
				for (AvioFlightSeat seatFirst : tempFirst) {
					flightSeats.add(seatFirst);
				}
			}

			for (AvioFlightSeat seat : flightSeats) {
				seat = seatService.saveSeat(seat);
			}
			avioFlight.setSeats(flightSeats);

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
			tempFlight.setEconomyClassSeats(avioFlightDTO.getEconomyClassSeats());
			tempFlight.setBusinessClassSeats(avioFlightDTO.getBusinessClassSeats());
			tempFlight.setFirstClassSeats(avioFlightDTO.getFirstClassSeats());

			List<AvioFlightSeat> tempEconomy = new ArrayList<AvioFlightSeat>();
			List<AvioFlightSeat> tempBusiness = new ArrayList<AvioFlightSeat>();
			List<AvioFlightSeat> tempFirst = new ArrayList<AvioFlightSeat>();

			// economy
			for (int i = 0; i < tempFlight.getEconomyClassSeats(); i++) {
				AvioFlightSeat tempEconomySeat = new AvioFlightSeat();
				tempEconomySeat.setFree(true);
				tempEconomySeat.setNumber(i + 1);
				tempEconomySeat.setClassOfSeat("economy");
				tempEconomy.add(tempEconomySeat);
			}
			// business
			for (int i = 0; i < tempFlight.getBusinessClassSeats(); i++) {
				AvioFlightSeat tempBusinessSeat = new AvioFlightSeat();
				tempBusinessSeat.setFree(true);
				tempBusinessSeat.setNumber(i + 1);
				tempBusinessSeat.setClassOfSeat("business");
				tempBusiness.add(tempBusinessSeat);
			}
			// first
			for (int i = 0; i < tempFlight.getFirstClassSeats(); i++) {
				AvioFlightSeat tempFirstSeat = new AvioFlightSeat();
				tempFirstSeat.setFree(true);
				tempFirstSeat.setNumber(i + 1);
				tempFirstSeat.setClassOfSeat("first");
				tempFirst.add(tempFirstSeat);
			}

			List<AvioFlightSeat> flightSeats = tempFlight.getSeats();

			if (!flightSeats.isEmpty()) {
				for (AvioFlightSeat tempSeat : flightSeats) {
					seatService.removeSeat(tempSeat.getId());
				}
				flightSeats.clear();
			}
			tempFlight.setSeats(flightSeats);
			flightSeats = new ArrayList<AvioFlightSeat>();
			if (!tempEconomy.isEmpty()) {
				for (AvioFlightSeat seatEconomy : tempEconomy) {
					flightSeats.add(seatEconomy);
				}
			}
			if (!tempBusiness.isEmpty()) {
				for (AvioFlightSeat seatBusiness : tempBusiness) {
					flightSeats.add(seatBusiness);
				}
			}
			if (!tempFirst.isEmpty()) {
				for (AvioFlightSeat seatFirst : tempFirst) {
					flightSeats.add(seatFirst);
				}
			}

			for (AvioFlightSeat seat : flightSeats) {
				seat = seatService.saveSeat(seat);
			}
			tempFlight.setSeats(flightSeats);
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

	// get all destinations
	@RequestMapping(value = "/destinations", method = RequestMethod.GET)
	public ResponseEntity<List<AddressDTO>> getAllDestinations() {
		List<AvioCompany> avioCompanies = avioCompanyService.getAllAvioCompanies();

		List<Address> destinations = new ArrayList<Address>();

		if (avioCompanies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		// metod za dodavanje svih destinacija bez ponavljanja
		for (AvioCompany avioCompany : avioCompanies) {
			Set<Address> tempDestinations = avioCompany.getDestinations();
			for (Address tempDestination : tempDestinations) {
				if (!destinations.contains(tempDestination)) {
					destinations.add(tempDestination);
				}
			}
		}

		List<AddressDTO> destinationsDTO = new ArrayList<AddressDTO>();

		for (Address address : destinations) {
			destinationsDTO.add(new AddressDTO(address));
		}

		return new ResponseEntity<>(destinationsDTO, HttpStatus.OK);
	}

	// search
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<List<AvioFlightDTO>> searchFlights(@RequestBody AvioFlightSearchDTO searchFlightsDTO) {
		List<AvioFlight> searchResult = avioFlightService.searchFlights(searchFlightsDTO, this.addressService);
		List<AvioFlightDTO> avioFlightsDTO = new ArrayList<AvioFlightDTO>();

		if (!searchResult.isEmpty()) {
			for (AvioFlight avioFlight : searchResult) {
				avioFlightsDTO.add(new AvioFlightDTO(avioFlight));
			}

		}
		return new ResponseEntity<>(avioFlightsDTO, HttpStatus.OK);
	}

	// get seats
	@RequestMapping(value = "/flights/{flightId}/seasts", method = RequestMethod.GET)
	public ResponseEntity<List<AvioFlightSeatDTO>> getAllSeatsOfFlight(@PathVariable("flightID") Long flightId) {
		AvioFlight avioFlight = avioFlightService.findAvioFlight(flightId);
		List<AvioFlightSeatDTO> seats = new ArrayList<AvioFlightSeatDTO>();

		if (avioFlight != null) {
			if (avioFlight.getSeats() != null) {
				for (AvioFlightSeat seat : avioFlight.getSeats()) {
					seats.add(new AvioFlightSeatDTO(seat));
				}
			}
		}
		return new ResponseEntity<>(seats, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/flights/{flightId}/setSeats/{number}", method = RequestMethod.POST)
	public ResponseEntity<Void> setSeatsOfFlight(@PathVariable("id") Long id, @PathVariable("flightId") Long flightId,
			@PathVariable("number") int number) {
		AvioCompany avioCompany = avioCompanyService.findAvioCompany(id);
		AvioFlight avioFlight = avioFlightService.findAvioFlight(flightId);

		if (avioCompany != null && avioFlight != null && avioFlight.isDeleted() == false) {
			if (avioCompany.getFlights().contains(avioFlight)) {
				avioFlight.getSeats().clear();
				for (int i = 0; i < number; i++) {
					AvioFlightSeat tempSeat = new AvioFlightSeat();
					tempSeat.setFree(true);
					tempSeat.setNumber(i + 1);
					avioFlight.getSeats().add(tempSeat);
				}
				avioFlight = avioFlightService.saveAvioFlight(avioFlight);

			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/flight/{id}/seats", method = RequestMethod.GET)
	public ResponseEntity<List<AvioFlightSeatDTO>> getSeatsOfFlight(@PathVariable("id") Long id) {

		AvioFlight avioFlight = avioFlightService.findAvioFlight(id);

		List<AvioFlightSeat> seats = new ArrayList<AvioFlightSeat>();

		if (avioFlight != null && !avioFlight.isDeleted()) {
			if (!avioFlight.getSeats().isEmpty()) {
				for (AvioFlightSeat seat : avioFlight.getSeats()) {
					seats.add(seat);
				}
			}
		}

		List<AvioFlightSeatDTO> seatsDTO = new ArrayList<AvioFlightSeatDTO>();

		if (!seats.isEmpty()) {
			for (AvioFlightSeat seat : seats) {
				seatsDTO.add(new AvioFlightSeatDTO(seat));
			}
		}

		return new ResponseEntity<>(seatsDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/flight/{id}/seats/{seatId}", method = RequestMethod.GET)
	public ResponseEntity<AvioFlightSeat> getSeat(@PathVariable("id") Long id, @PathVariable("seatId") Long seatId) {
		AvioFlight avioFlight = avioFlightService.findAvioFlight(id);

		AvioFlightSeat seat = seatService.findSeat(seatId);

		if (avioFlight != null && !avioFlight.isDeleted() && seat != null && !seat.isDeleted()) {
			if (avioFlight.getSeats().contains(seat)) {
				return new ResponseEntity<>(seat, HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/flight/reservation", method = RequestMethod.POST)
	public ResponseEntity<AvioFlightReservationDTO> makeAvioFlightReservation(
			@RequestBody AvioFlightReservationDTO reservation) {

		AvioFlight avioFlight = avioFlightService.findAvioFlight(reservation.getAvioFlight().getId());
		User user = userService.findUser(reservation.getUser().getId());
		AvioFlightSeat seat = seatService.findSeat(reservation.getSeat().getId());

		if (avioFlight != null && user != null && seat != null && !avioFlight.isDeleted() && !seat.isDeleted()) {
			AvioFlightReservation newReservation = new AvioFlightReservation();
			newReservation.setAvioFlight(avioFlight);
			newReservation.setUser(user);
			seat.setFree(false);
			seat = seatService.updateSeat(seat);
			newReservation.setSeat(seat);
			newReservation.setDeleted(false);
			newReservation.setRatingFlight(-1);
			newReservation.setRatingCompany(-1);

			newReservation = reservationService.saveReservation(newReservation);

			return new ResponseEntity<>(new AvioFlightReservationDTO(newReservation), HttpStatus.OK);

		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}/avgAvioRating", method = RequestMethod.GET)
	public Double getAvioAvgRating(@PathVariable Long id) {

		return avioCompanyService.getAvgRating(id);
	}

	// generisanje izvestaja od strane admina, preutorize admin, prima id rentCara
	@RequestMapping(value = "/{idAvio}/generate", method = RequestMethod.POST)
	public ResponseEntity<AvioReport> generateReport(@PathVariable Long idAvio, @RequestBody DateRange dateRange) {

		AvioReport rcr = avioCompanyService.generateReport(idAvio, dateRange);

		return new ResponseEntity<>(rcr, HttpStatus.CREATED);
	}

	// decline
	@RequestMapping(value = "/{idFlight}/flight/{idUser}/declineReservation/{idInvite}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> declineReservation(@PathVariable("idUser") Long idUser,
			@PathVariable("idFlight") Long idFlight, @PathVariable("idInvite") Long idInvite) {

		User user = userService.findUser(idUser);
		AvioFlight flight = avioFlightService.findAvioFlight(idFlight);
		InviteForFlight invite = inviteService.findInvite(idInvite);

		List<AvioFlightReservation> reservations = reservationService.getAllReservation();

		if (user != null && flight != null && !reservations.isEmpty() && flight.isDeleted() == false) {
			System.out.println("USO PRVI IF");
			for (AvioFlightReservation reservation : reservations) {
				if (reservation != null && reservation.isDeleted() == false) {
					System.out.println("USO DRUGI IF");
					if (reservation.getUser().equals(user) && reservation.getAvioFlight().equals(flight)) {
						System.out.println("USERNAME: " + reservation.getUser().getUsername());
						System.out.println("FLIGHT: " + reservation.getAvioFlight().getStartLocation());
						AvioFlightSeat seat = seatService.findSeat(reservation.getSeat().getId());
						if (seat != null && seat.isDeleted() == false && seat.isFree() == false) {
							System.out.println("USO TRECI IF");
							seat.setFree(true);
							seat = seatService.updateSeat(seat);
						}
						System.out.println("USO U DECLINE");
						inviteService.removeInvite(invite.getId());
						reservationService.removeReservation(reservation.getId());
					}
				}
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
