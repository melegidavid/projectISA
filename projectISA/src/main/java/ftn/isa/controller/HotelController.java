package ftn.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.model.Hotel;
import ftn.isa.service.HotelService;

@RestController
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@RequestMapping(value="/hotels",method = RequestMethod.GET)
	public List<Hotel> getHotels() {
		return hotelService.getAllHotels();
	}
	
	@RequestMapping(value="/hotels",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addHotel(@RequestBody Hotel hotel) {
		hotelService.addHotel(hotel);
	}
}
