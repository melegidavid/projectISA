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

import ftn.isa.dto.HotelDTO;
import ftn.isa.dto.HotelMenuItemDTO;
import ftn.isa.dto.HotelRoomDTO;
import ftn.isa.model.Address;
import ftn.isa.model.Hotel;
import ftn.isa.model.HotelMenuItem;
import ftn.isa.model.HotelRoom;
import ftn.isa.service.AddressService;
import ftn.isa.service.HotelMenuItemService;
import ftn.isa.service.HotelRoomService;
import ftn.isa.service.HotelService;

@RestController
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
	
	@RequestMapping(value="{/id}", method = RequestMethod.GET)
	public ResponseEntity<HotelDTO> findHotel(@PathVariable("id") Long id) {
		Hotel hotel = hotelService.findHotel(id);
		
		if(hotel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new HotelDTO(hotel), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<HotelDTO> addHotel(@RequestBody HotelDTO hotelDTO) {
		Hotel hotel = new Hotel();
		Address address = addressService.findOne(hotelDTO.getAddressDTO().getId());
		
		if(address == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		hotel.setName(hotelDTO.getName());
		hotel.setDescription(hotelDTO.getDescription());
		hotel.setAddress(address);
		
		hotel = hotelService.saveHotel(hotel);
		
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
} 
