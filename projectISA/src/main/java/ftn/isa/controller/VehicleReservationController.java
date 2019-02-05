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

import ftn.isa.dto.RatingUpdateDTO;
import ftn.isa.dto.ReservationDTO;
import ftn.isa.model.User;
import ftn.isa.model.VehicleReservation;
import ftn.isa.service.UserService;
import ftn.isa.service.VehicleReservationService;

@RestController
@RequestMapping(value = "vehicle_reservations")
public class VehicleReservationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VehicleReservationService vehicleReservationService;
	
	@RequestMapping(value = "/{username}" , method = RequestMethod.GET)
	public ResponseEntity<List<ReservationDTO>> getAllReservations(@PathVariable String username) {

		User user = userService.getUserByUsername(username);
		
		List<VehicleReservation> reservations = user.getVehicleReservations();		
		List<ReservationDTO> reservationDTOs = new ArrayList<ReservationDTO>();

		for (VehicleReservation vr : reservations) {
			reservationDTOs.add(new ReservationDTO(vr));
		}

		return new ResponseEntity<>(reservationDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateVehicleRating" , method = RequestMethod.POST)
	public ResponseEntity<List<ReservationDTO>> updateVehicleRating(@RequestBody RatingUpdateDTO ratingUpdate) {

		VehicleReservation vr = vehicleReservationService.findOne(ratingUpdate.getIdReservation());
		vr.setVehicleRating(ratingUpdate.getNewValue());
		vehicleReservationService.update(vr);
		
        User user = vr.getUser();
		
		List<VehicleReservation> reservations = user.getVehicleReservations();		
		List<ReservationDTO> reservationDTOs = new ArrayList<ReservationDTO>();

		for (VehicleReservation vres : reservations) {
			reservationDTOs.add(new ReservationDTO(vres));
		}

		return new ResponseEntity<>(reservationDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateRentCarRating" , method = RequestMethod.POST)
	public ResponseEntity<List<ReservationDTO>> updateRentCarRating(@RequestBody RatingUpdateDTO ratingUpdate) {

		VehicleReservation vr = vehicleReservationService.findOne(ratingUpdate.getIdReservation());
		vr.setRentCarRating(ratingUpdate.getNewValue());
		vehicleReservationService.update(vr);
		
		User user = vr.getUser();
		
		List<VehicleReservation> reservations = user.getVehicleReservations();		
		List<ReservationDTO> reservationDTOs = new ArrayList<ReservationDTO>();

		for (VehicleReservation vres : reservations) {
			reservationDTOs.add(new ReservationDTO(vres));
		}

		return new ResponseEntity<>(reservationDTOs, HttpStatus.OK);
	}

}
