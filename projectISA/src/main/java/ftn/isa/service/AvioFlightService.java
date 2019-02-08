package ftn.isa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.dto.AvioFlightSearchDTO;
import ftn.isa.model.Address;
import ftn.isa.model.AvioFlight;
import ftn.isa.repository.AvioFlightRepository;

@Service
@Transactional(readOnly = true)
public class AvioFlightService {

	@Autowired
	private AvioFlightRepository avioFlightRepository;

	// get all
	public List<AvioFlight> getAllAvioFlights() {
		List<AvioFlight> list = new ArrayList<AvioFlight>();
		list = avioFlightRepository.findAll();
		List<AvioFlight> result = new ArrayList<AvioFlight>();
		for (AvioFlight avioFlight : list) {
			if (avioFlight.isDeleted() != true) {
				result.add(avioFlight);
			}
		}
		return result;
	}

	// get one
	public AvioFlight findAvioFlight(Long id) {
		return avioFlightRepository.getOne(id);
	}

	// save
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AvioFlight saveAvioFlight(AvioFlight avioFlight) {
		return avioFlightRepository.save(avioFlight);
	}

	// update
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
	public AvioFlight updateAvioFlight(AvioFlight avioFlight) {
		return avioFlightRepository.save(avioFlight);
	}

	// delete
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void removeAvioFlight(Long id) {
		avioFlightRepository.getOne(id).setDeleted(true);
		avioFlightRepository.save(avioFlightRepository.getOne(id));
	}

	// search
	public List<AvioFlight> searchFlights(AvioFlightSearchDTO searchFlights, AddressService addressService) {

		List<AvioFlight> allFlights = this.getAllAvioFlights();
		List<AvioFlight> searchResult = new ArrayList<AvioFlight>();

		String typeOfFlight = searchFlights.getTypeOfFlight();

		if (typeOfFlight.equals("oneWay")) {
			for (AvioFlight avioFlight : allFlights) {
				if (searchFlights.getFromLocation() != null) {
					Address tempFromLocation = addressService.findOne(searchFlights.getFromLocation().getId());
					Address tempToLocation = addressService.findOne(searchFlights.getToLocation().getId());
					LocalDate tempDepartureDate = searchFlights.getDepartureDate();
					if (tempFromLocation != null && tempToLocation != null && tempDepartureDate != null) {
						if (avioFlight.getStartLocation().equals(tempFromLocation)
								&& avioFlight.getEndLocation().equals(tempToLocation)
								&& avioFlight.getDateTimeStart().toLocalDate().equals(tempDepartureDate)) {
							if (!avioFlight.isDeleted()) {
								searchResult.add(avioFlight);
							}
						}
					}
				}
			}
		} else if (typeOfFlight.equals("roundTrip")) {
			for (AvioFlight avioFlight : allFlights) {
				Address tempFromLocation = addressService.findOne(searchFlights.getFromLocation().getId());
				Address tempToLocation = addressService.findOne(searchFlights.getToLocation().getId());
				LocalDate tempDepartureDate = searchFlights.getDepartureDate();
				LocalDate tempReturnDate = searchFlights.getReturnDate();
				// trazi se let A - B
				if (tempFromLocation != null && tempToLocation != null && tempDepartureDate != null
						&& tempReturnDate != null) {
					if (avioFlight.getStartLocation().equals(tempFromLocation)) {
						if (avioFlight.getEndLocation().equals(tempToLocation)) {
							if (avioFlight.getDateTimeStart().toLocalDate().equals(tempDepartureDate)) {
								// trazi se let B - A
								for (AvioFlight backAvioFlight : allFlights) {
									if (backAvioFlight.getStartLocation().equals(tempToLocation)) {
										if (backAvioFlight.getEndLocation().equals(tempFromLocation)) {
											if (backAvioFlight.getDateTimeStart().toLocalDate()
													.equals(tempReturnDate)) {
												if (!avioFlight.isDeleted() && !backAvioFlight.isDeleted()) {
													// dodamo let A-B
													searchResult.add(avioFlight);
													// dodamo let B-A
													searchResult.add(backAvioFlight);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		/*
		 * else if (typeOfFlight.equals("multiCity")) {
		 * 
		 * }
		 */

		return searchResult;
	}
}

