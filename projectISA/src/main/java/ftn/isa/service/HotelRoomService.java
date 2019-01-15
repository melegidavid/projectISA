package ftn.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.HotelRoom;
import ftn.isa.repository.HotelRoomRepository;

@Service
public class HotelRoomService {

	@Autowired 
	private HotelRoomRepository hotelRoomRepository;
	
	public HotelRoom findHotelRoom(Long roomId) {
		return hotelRoomRepository.getOne(roomId);
	}
	
	public HotelRoom saveHotelRoom(HotelRoom room) {
		return hotelRoomRepository.save(room);
	}
	
	public HotelRoom updateHotelRoom(HotelRoom room) {
		return hotelRoomRepository.save(room);
	}
	
	public void removeHotelRoom(HotelRoom room) {
		hotelRoomRepository.delete(room);
	}
}
