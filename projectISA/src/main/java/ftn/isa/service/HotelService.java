package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.Hotel;
import ftn.isa.model.HotelRoom;
import ftn.isa.repository.HotelRepository;
import ftn.isa.repository.HotelRoomRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	public List<Hotel> getAllHotels() {
		List<Hotel> list = new ArrayList<Hotel>();
		hotelRepository.findAll().forEach(list::add);
		return list;
	}
	
	public Hotel findHotel(Long id) {
		return hotelRepository.getOne(id);
	}
	
	public Hotel saveHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}
	
	public Hotel updateHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}
	
	public void removeHotel(Hotel hotel) {
		hotelRepository.delete(hotel);
	}
}
