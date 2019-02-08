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

import ftn.isa.dto.AvioFlightReservationDTO;
import ftn.isa.dto.RatingUpdateDTO;
import ftn.isa.dto.ReservationDTO;
import ftn.isa.dto.RoomReservationDTO;
import ftn.isa.model.AvioFlightReservation;
import ftn.isa.model.RoomReservation;
import ftn.isa.model.User;
import ftn.isa.model.VehicleReservation;
import ftn.isa.service.AvioFlightReservationService;
import ftn.isa.service.HotelRoomService;
import ftn.isa.service.UserService;
import ftn.isa.service.VehicleReservationService;

@RestController
@RequestMapping(value = "vehicle_reservations")
public class VehicleReservationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VehicleReservationService vehicleReservationService;
	
	@Autowired
	private HotelRoomService roomReservationService;
	
	@Autowired
	private AvioFlightReservationService avioReservationService;
	
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
	
	@RequestMapping(value = "/{idReservation}/cancelVehicleReservation" , method = RequestMethod.GET)
	public ResponseEntity<List<ReservationDTO>> cancelVehicleReservation(@PathVariable Long idReservation) {

		User user = vehicleReservationService.findOne(idReservation).getUser();
		
		System.out.println("duzina je bila " + vehicleReservationService.findAll().size() );
		
		
		vehicleReservationService.remove(idReservation);
		
		System.out.println("duzina je sad " + vehicleReservationService.findAll().size() );
		
		System.out.println("Usao u kontroler, brise " + idReservation + " a user je" + user.getUsername() );
		 
		List<VehicleReservation> reservations = user.getVehicleReservations();		
		List<ReservationDTO> reservationDTOs = new ArrayList<ReservationDTO>();

		for (VehicleReservation vr : reservations) {
			reservationDTOs.add(new ReservationDTO(vr));
		}

		return new ResponseEntity<>(reservationDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{idReservation}/cancelRoomReservation" , method = RequestMethod.GET)
	public ResponseEntity<List<RoomReservationDTO>> cancelRoomReservation(@PathVariable Long idReservation) {

		
		User user = roomReservationService.findRoomReservation(idReservation).getUser();
		
		System.out.println("duzina je bila " + user.getRoomReservations().size() );
		
		roomReservationService.removeRoomReservation(idReservation);	
		System.out.println(roomReservationService.findRoomReservation(idReservation));
		
		
        System.out.println("duzina je sad " + user.getRoomReservations().size() );
		
		System.out.println("Usao u kontroler, brise " + idReservation + " a user je" + user.getUsername() );
		 
		
		List<RoomReservation> reservations = user.getRoomReservations();
		List<RoomReservationDTO> reservationDTOs = new ArrayList<RoomReservationDTO>();

		for (RoomReservation vr : reservations) {
			reservationDTOs.add(new RoomReservationDTO(vr));
		}

		return new ResponseEntity<>(reservationDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{idReservation}/cancelAvioReservation" , method = RequestMethod.GET)
	public ResponseEntity<List<AvioFlightReservationDTO>> cancelAvioReservation(@PathVariable Long idReservation) {

		User user = avioReservationService.findReservation(idReservation).getUser();

		System.out.println("duzina je bila " + this.avioReservationService.getAllReservation().size());
		
		
		avioReservationService.removeReservation(idReservation);
		
		System.out.println("duzina je sad " + this.avioReservationService.getAllReservation().size());
		
		System.out.println("Usao u kontroler, brise " + idReservation + " a user je" + user.getUsername() );
		 
		List<AvioFlightReservation> reservations = user.getAvioReservations();
		List<AvioFlightReservationDTO> reservationDTOs = new ArrayList<AvioFlightReservationDTO>();

		for (AvioFlightReservation vr : reservations) {
			reservationDTOs.add(new AvioFlightReservationDTO(vr));
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
	
	@RequestMapping(value = "/updateRoomRating" , method = RequestMethod.POST)
	public ResponseEntity<List<RoomReservationDTO>> updateRoomRating(@RequestBody RatingUpdateDTO ratingUpdate) {

		RoomReservation vr = roomReservationService.findRoomReservation(ratingUpdate.getIdReservation());
		
		vr.setRoomRating(ratingUpdate.getNewValue());
		roomReservationService.updateReservation(vr);
		
        User user = vr.getUser();
		
		List<RoomReservation> reservations = user.getRoomReservations();		
		List<RoomReservationDTO> reservationDTOs = new ArrayList<RoomReservationDTO>();

		for (RoomReservation vres : reservations) {
			reservationDTOs.add(new RoomReservationDTO(vres));
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
	
	@RequestMapping(value = "/updateHotelRating" , method = RequestMethod.POST)
	public ResponseEntity<List<RoomReservationDTO>> updateHotelRating(@RequestBody RatingUpdateDTO ratingUpdate) {

		RoomReservation vr = roomReservationService.findRoomReservation(ratingUpdate.getIdReservation());
		
		vr.setHotelRating(ratingUpdate.getNewValue());
		roomReservationService.updateReservation(vr);
		
		User user = vr.getUser();
		
		List<RoomReservation> reservations = user.getRoomReservations();		
		List<RoomReservationDTO> reservationDTOs = new ArrayList<RoomReservationDTO>();

		for (RoomReservation vres : reservations) {
			reservationDTOs.add(new RoomReservationDTO(vres));
		}

		return new ResponseEntity<>(reservationDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateFlightRating" , method = RequestMethod.POST)
	public ResponseEntity<List<AvioFlightReservationDTO>> updateFlightRating(@RequestBody RatingUpdateDTO ratingUpdate) {

		AvioFlightReservation vr = avioReservationService.findReservation(ratingUpdate.getIdReservation());
		
		vr.setRatingFlight(ratingUpdate.getNewValue());
		avioReservationService.updateReservation(vr);
		
		User user = vr.getUser();
		
		List<AvioFlightReservation> reservations = user.getAvioReservations();		
		List<AvioFlightReservationDTO> reservationDTOs = new ArrayList<AvioFlightReservationDTO>();

		for (AvioFlightReservation vres : reservations) {
			reservationDTOs.add(new AvioFlightReservationDTO(vres));
		}

		return new ResponseEntity<>(reservationDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateAvioRating" , method = RequestMethod.POST)
	public ResponseEntity<List<AvioFlightReservationDTO>> updateAvioRating(@RequestBody RatingUpdateDTO ratingUpdate) {

		AvioFlightReservation vr = avioReservationService.findReservation(ratingUpdate.getIdReservation());
		
		vr.setRatingCompany(ratingUpdate.getNewValue());
	  	avioReservationService.updateReservation(vr);
		
		User user = vr.getUser();
		
		List<AvioFlightReservation> reservations = user.getAvioReservations();		
		List<AvioFlightReservationDTO> reservationDTOs = new ArrayList<AvioFlightReservationDTO>();

		for (AvioFlightReservation vres : reservations) {
			reservationDTOs.add(new AvioFlightReservationDTO(vres));
		}

		return new ResponseEntity<>(reservationDTOs, HttpStatus.OK);
	}

}
