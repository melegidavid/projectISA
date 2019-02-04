package ftn.isa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.dto.HotelDTO;
import ftn.isa.dto.HotelMenuItemDTO;
import ftn.isa.dto.HotelMenuItemReservationDTO;
import ftn.isa.dto.HotelRoomDTO;
import ftn.isa.dto.RoomReservationDTO;
import ftn.isa.dto.RoomSearchDTO;
import ftn.isa.model.Address;
import ftn.isa.model.Hotel;
import ftn.isa.model.HotelMenuItem;
import ftn.isa.model.HotelMenuItemReservation;
import ftn.isa.model.HotelRoom;
import ftn.isa.model.RoomReservation;
import ftn.isa.model.User;
import ftn.isa.service.AddressService;
import ftn.isa.service.HotelMenuItemService;
import ftn.isa.service.HotelRoomService;
import ftn.isa.service.HotelService;
import ftn.isa.service.UserService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200") 
@RequestMapping(value="/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private HotelRoomService hotelRoomService;
	
	@Autowired
	private HotelMenuItemService hotelMenuItemService;
	
	@Autowired
	private UserService userService;
	
	//Kontroler za hotele
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<HotelDTO>> getHotels() {
		List<Hotel> hotels = hotelService.getAllHotels();
		
		List<HotelDTO> hotelsDTO = new ArrayList<>();
		for(Hotel h: hotels) {
			hotelsDTO.add(new HotelDTO(h));
		}
		
		return new ResponseEntity<>(hotelsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<HotelDTO> findHotel(@PathVariable("id") Long id) {
		Hotel hotel = hotelService.findHotel(id);
		
		if(hotel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new HotelDTO(hotel), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<HotelDTO> addHotel(@RequestBody HotelDTO hotelDTO) {
		System.out.println("USAO U DODAVANJE HOTELA");
		Hotel hotel = new Hotel();
		//Address address = addressService.findOne(hotelDTO.getAddressDTO().getId());
		
//		if(address == null) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
		
		Address address = new Address();
		address.setCountry(hotelDTO.getAddressDTO().getCountry());
		address.setCity(hotelDTO.getAddressDTO().getCity());
		address.setPostalCode(hotelDTO.getAddressDTO().getNumber());
		address.setStreet(hotelDTO.getAddressDTO().getStreet());
		address.setNumber(hotelDTO.getAddressDTO().getNumber());
		
		address = addressService.save(address);
		
		
		hotel.setName(hotelDTO.getName());
		hotel.setDescription(hotelDTO.getDescription());
		hotel.setAddress(address);
		
		hotel = hotelService.saveHotel(hotel);
		System.out.println("HOTEL SACUVAN");
		
		return new ResponseEntity<>(new HotelDTO(hotel), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<HotelDTO> updateHotel(@RequestBody HotelDTO hotelDTO) {
		Hotel hotel = hotelService.findHotel(hotelDTO.getId());
		if(hotel == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		hotel.setName(hotelDTO.getName());
		//hotel.setAddress(hotelDTO.getAddress());
		hotel.setDescription(hotelDTO.getDescription());
		
		hotel = hotelService.saveHotel(hotel);
		return new ResponseEntity<>(new HotelDTO(hotel), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeHotel(@PathVariable("id") Long id) {
		
		Hotel hotel = hotelService.findHotel(id);
		if(hotel != null) {
			hotelService.removeHotel(hotel);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//Kontroler za hotelske sobe 
	
	
	@RequestMapping(value="/{id}/rooms", method = RequestMethod.GET)
	public ResponseEntity<List<HotelRoomDTO>> getHotelRooms(@PathVariable("id") Long id) {
		Hotel hotel = hotelService.findHotel(id);
		
		if(hotel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<HotelRoom> rooms = hotel.getRooms();
		
		if(rooms.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<HotelRoomDTO> roomsDTO	= new ArrayList<>();
		
		for(HotelRoom room : rooms) {
			roomsDTO.add(new HotelRoomDTO(room));
		}
		
		return new ResponseEntity<>(roomsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/rooms/{roomId}", method = RequestMethod.GET)
	public ResponseEntity<HotelRoomDTO> getHotelRoom(@PathVariable("roomId") Long roomId) {
		
		HotelRoom room = hotelRoomService.findHotelRoom(roomId);
		
		if(room == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HotelRoomDTO roomDTO = new HotelRoomDTO(room);
		
		return new ResponseEntity<>(roomDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/rooms", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<HotelRoomDTO> addHotelRoom(@PathVariable("id") Long hotelId, @RequestBody HotelRoomDTO roomDTO) {
		Hotel hotel = hotelService.findHotel(hotelId);
		
		if(hotel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HotelRoom room = new HotelRoom();
		room.setNumber(roomDTO.getNumber());
		room.setFree(roomDTO.isFree());
		room.setHotel(hotel);
		room.setDescription(roomDTO.getDescription());
		room.setPrice(roomDTO.getPrice());
		room.setBedNumber(roomDTO.getBedNumber());
		
		room = hotelRoomService.saveHotelRoom(room);
		
		return new ResponseEntity<>(new HotelRoomDTO(room), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{hotelId}/rooms/{roomId}", method = RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<HotelRoomDTO> updateHotelRoom(@PathVariable("hotelId") Long hotelId, @PathVariable("roomId") Long roomId, @RequestBody HotelRoomDTO roomDTO) {
		
		Hotel hotel = hotelService.findHotel(hotelId);
		
		HotelRoom room = hotelRoomService.findHotelRoom(roomId);
		if(room == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		room.setNumber(roomDTO.getNumber());
		room.setFree(roomDTO.isFree());
		room.setHotel(hotel);
		room.setDescription(roomDTO.getDescription());
		room.setPrice(roomDTO.getPrice());
		room.setBedNumber(roomDTO.getBedNumber());
		
		room = hotelRoomService.updateHotelRoom(room);
		
		return new ResponseEntity<>(new HotelRoomDTO(room), HttpStatus.OK);
	}
	
	@RequestMapping(value="/rooms/{roomId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeHotelRoom(@PathVariable("roomId") Long roomId) {
		
		HotelRoom room = hotelRoomService.findHotelRoom(roomId);
		
		if(room == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			hotelRoomService.removeHotelRoom(room);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	
	//Kontroler za menu hotela
	
	@RequestMapping(value="/{id}/menu", method=RequestMethod.GET)
	public ResponseEntity<List<HotelMenuItemDTO>> getHotelMenu(@PathVariable("id") Long hotelId) {
		
		Hotel hotel = hotelService.findHotel(hotelId);
		if(hotel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<HotelMenuItem> menu = hotel.getMenu();
		
		if(menu.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<HotelMenuItemDTO> menuDTO = new ArrayList<>();
		for(HotelMenuItem item : menu) {
			menuDTO.add(new HotelMenuItemDTO(item));
		}
		
		return new ResponseEntity<>(menuDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{hotelId}/menu/{itemId}", method=RequestMethod.GET)
	public ResponseEntity<HotelMenuItemDTO> getHotelMenuItem(@PathVariable("hotelId") Long hotelId, @PathVariable("menuId") Long menuId) {
		
		HotelMenuItem item = hotelMenuItemService.findHotelMenuItem(menuId);
		if(item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HotelMenuItemDTO itemDTO = new HotelMenuItemDTO(item);
		
		return new ResponseEntity<>(itemDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{hotelId}/menu", method=RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<HotelMenuItemDTO> addHotelMenuItem(@PathVariable("hotelId") Long hotelId, @RequestBody HotelMenuItemDTO itemDTO) {
		
		Hotel hotel = hotelService.findHotel(hotelId);
		if(hotel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HotelMenuItem item = new HotelMenuItem();
		item.setServiceName(itemDTO.getServiceName());
		item.setPrice(itemDTO.getPrice());
		item.setDescription(itemDTO.getDescription());
		item.setHotel(hotel);
		
		item = hotelMenuItemService.saveHotelMenuItem(item);
		
		return new ResponseEntity<>(new HotelMenuItemDTO(item), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{hotelId}/menu/{itemId}", method=RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<HotelMenuItemDTO> updateHotelMenuItem(@PathVariable("hotelId") Long hotelId, @PathVariable("itemId") Long itemId, @RequestBody HotelMenuItemDTO itemDTO){
		
		Hotel hotel = hotelService.findHotel(hotelId);
		if(hotel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HotelMenuItem item = hotelMenuItemService.findHotelMenuItem(itemId);
		if(item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		item.setServiceName(itemDTO.getServiceName());
		item.setPrice(itemDTO.getPrice());
		item.setDescription(itemDTO.getDescription());
		item.setHotel(hotel); // da li ovde treba hotel, ili hotel od itemDTO?
		
		item = hotelMenuItemService.updateHotelMenuItem(item);
		
		return new ResponseEntity<>(new HotelMenuItemDTO(item), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{hotelId}/menu/{itemId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> removeHotelMenuItem(@PathVariable("hotelId") Long hotelId, @PathVariable("itemId") Long itemId) {
		
		HotelMenuItem item = hotelMenuItemService.findHotelMenuItem(itemId);
		if(item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			hotelMenuItemService.removeHotelMenuItem(item);
			return new ResponseEntity<>(HttpStatus.OK);			
		}
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST) 
	public ResponseEntity<List<HotelDTO>> searchHotels(@RequestBody RoomSearchDTO rsDTO) {
		
		List<HotelDTO> hotelsDTO = new ArrayList<>();
		List<Hotel> hotels = hotelService.getAllHotels();
		
		Date startDate = rsDTO.getStartDate();
		Date endDate = rsDTO.getEndDate();
		
		if(startDate==null || endDate==null) { // ne treba dozvoliti da se ovo desi!
			for(Hotel hotel: hotels) {
				hotelsDTO.add(new HotelDTO(hotel));
			}
			return new ResponseEntity<>(hotelsDTO, HttpStatus.OK);
		}
		
		for(Hotel hotel: hotels) {
			List<HotelRoom> rooms = new ArrayList<>(); 
			rooms =	hotelRoomService.freeHotelRooms(startDate, endDate, hotel);
			System.out.println(hotel.getName() + " size: " + rooms.size());
			if(rooms.size() > 0) {
				hotelsDTO.add(new HotelDTO(hotel));
			}
		}
		
		return new ResponseEntity<>(hotelsDTO, HttpStatus.OK);
	}
	
	
	//VRACA SLOBODNE SOBE ZA ODREDJENI HOTEL ZA ODREDJENI VREMENSKI PERIOD
	@RequestMapping(value="/{id}/freeRooms", method=RequestMethod.POST)
	public ResponseEntity<List<HotelRoomDTO>> getFreeHotelRooms(@PathVariable("id") Long id, @RequestBody RoomSearchDTO rsDTO) {
		Hotel hotel = hotelService.findHotel(id);
		if(hotel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<HotelRoom> rooms = hotelRoomService.freeHotelRooms(rsDTO.getStartDate(), rsDTO.getEndDate(), hotel);
		List<HotelRoomDTO> roomsDTO = new ArrayList<>();
		
		if(rooms == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		for(HotelRoom r: rooms) {
			roomsDTO.add(new HotelRoomDTO(r));
		}
		
		return new ResponseEntity<>(roomsDTO, HttpStatus.OK);
	}
	
	//VRACA SLOBODNE SOBE KOJE ZADOVOLJAVAJU BROJ GOSTIJU I ZELJENI BROJ SOBA ZA ODREDJENI VREMENSKI PERIOD
	@RequestMapping(value="{id}/search", method=RequestMethod.POST)
	public ResponseEntity<List<HotelRoomDTO>> searchHotelRooms(@PathVariable("id") Long id, @RequestBody RoomSearchDTO rsDTO) {
		Hotel hotel = hotelService.findHotel(id);
		
		if(hotel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<HotelRoomDTO> roomsDTO = new ArrayList<>();
		
		//sve slobodne sobe u hotelu
		List<HotelRoom> rooms = hotelRoomService.freeHotelRooms(rsDTO.getStartDate(), rsDTO.getEndDate(), hotel);
		
		if(rsDTO.getRoomsNumber() > rooms.size()) {
			//ukoliko je trazeni broj soba veci od broja slobodnih soba, posalji praznu listu
			return new ResponseEntity<>(roomsDTO, HttpStatus.OK);
		}
		
		//suma kreveta u slobodnim sobama
		int avaliableBeds = 0;
		for(HotelRoom r: rooms) {
			avaliableBeds += r.getBedNumber();
		}
		
		if(rsDTO.getGuestsNumber() > avaliableBeds) {
			//ako ima manje kreveta nego sto ima gostiju vrati praznu listu
			return new ResponseEntity<>(roomsDTO, HttpStatus.OK);
		}
		
		for(HotelRoom r: rooms) {
			//vracamo listu soba ukoliko ima dovoljno kreveta i dovoljno soba
			roomsDTO.add(new HotelRoomDTO(r));
		}
		
		return new ResponseEntity<>(roomsDTO, HttpStatus.OK);	
	}
	
	//REZERVACIJA SOBE
	@RequestMapping(value="reservation/{roomId}", method=RequestMethod.POST)
	public ResponseEntity<RoomReservationDTO> makeRoomReservation(@PathVariable("roomId") Long roomId, @RequestBody RoomReservationDTO rrDTO) {
		HotelRoom room = hotelRoomService.findHotelRoom(roomId);
		User user = userService.getUserByUsername(rrDTO.getUsername());
		
		if(room == null || user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		RoomReservation reservation = new RoomReservation();
		reservation.setStartReservation(rrDTO.getStartReservation());
		reservation.setEndReservation(rrDTO.getEndReservation());
		reservation.setBelongsToRoom(room);
		reservation.setUser(user);
		reservation.setPrice(rrDTO.getPrice());
		
		reservation = hotelRoomService.makeRoomReservation(reservation);
		
		return new ResponseEntity<>(new RoomReservationDTO(reservation), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="item_reservation/{itemId}", method=RequestMethod.POST)
	public ResponseEntity<HotelMenuItemReservationDTO> makeHotelMenuItemReservation(@PathVariable("itemId") Long itemId, @RequestBody HotelMenuItemReservationDTO reservationDTO) {
		HotelMenuItem item = hotelMenuItemService.findHotelMenuItem(itemId);
		User user = userService.getUserByUsername(reservationDTO.getUsername());
		
		if(item == null || user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HotelMenuItemReservation reservation = new HotelMenuItemReservation();
		reservation.setStartReservation(reservationDTO.getStartReservation());
		reservation.setEndReservation(reservationDTO.getEndReservation());
		reservation.setPrice(reservationDTO.getPrice());
		reservation.setBelongsToHotelMenuItem(item);
		reservation.setUser(user);
		
		reservation = hotelMenuItemService.makeReservation(reservation);
		
		return new ResponseEntity<>(new HotelMenuItemReservationDTO(reservation), HttpStatus.CREATED);
	}
} 









