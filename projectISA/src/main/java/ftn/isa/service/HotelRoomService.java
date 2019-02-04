package ftn.isa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.Hotel;
import ftn.isa.model.HotelRoom;
import ftn.isa.model.RoomReservation;
import ftn.isa.repository.HotelRoomRepository;
import ftn.isa.repository.RoomReservationRepository;

@Service
public class HotelRoomService {

	@Autowired 
	private HotelRoomRepository hotelRoomRepository;
	
	@Autowired
	private RoomReservationRepository roomReservationRepository;
	
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
	
	public List<HotelRoom> freeHotelRooms(Date startDate, Date endDate, Hotel hotel) {
		List<HotelRoom> freeRooms = new ArrayList<>();
		List<HotelRoom> rooms = hotel.getRooms();
		
		for(HotelRoom room: rooms) {
			boolean isReserved = false;
			List<RoomReservation> roomReservations = room.getReseravations();
			for(RoomReservation roomReservation: roomReservations) {
				if(!(endDate.before(roomReservation.getStartReservation()) || startDate.after(roomReservation.getEndReservation()))) {
					isReserved = true;
				}
			}
			if(!isReserved) {
				freeRooms.add(room);
			}
		}
		return freeRooms;
	}
	
	public RoomReservation makeRoomReservation(RoomReservation roomReservation) {
		return roomReservationRepository.save(roomReservation);
	}
	
}








