package ftn.isa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.model.Hotel;
import ftn.isa.model.HotelRoom;
import ftn.isa.model.RoomReservation;
import ftn.isa.repository.HotelRoomRepository;
import ftn.isa.repository.RoomReservationRepository;

@Service
@Transactional(readOnly = true)
public class HotelRoomService {

	@Autowired 
	private HotelRoomRepository hotelRoomRepository;
	
	@Autowired
	private RoomReservationRepository roomReservationRepository;
	
	public HotelRoom findHotelRoom(Long roomId) {
		return hotelRoomRepository.getOne(roomId);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public HotelRoom saveHotelRoom(HotelRoom room) {
		return hotelRoomRepository.save(room);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
	public HotelRoom updateHotelRoom(HotelRoom room) {
		return hotelRoomRepository.save(room);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void removeHotelRoom(HotelRoom room) {
		HotelRoom r = this.findHotelRoom(room.getId());
		r.setDeleted(true);
		this.updateHotelRoom(r);
	}
	
	public List<HotelRoom> freeHotelRooms(Date startDate, Date endDate, Hotel hotel) {
		List<HotelRoom> freeRooms = new ArrayList<>();
		List<HotelRoom> rooms = hotel.getRooms();
		
		for(HotelRoom room: rooms) {
			if(!room.isDeleted()) {
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
		}
		return freeRooms;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RoomReservation makeRoomReservation(RoomReservation roomReservation) {
		return roomReservationRepository.save(roomReservation);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
	public RoomReservation updateReservation(RoomReservation roomReservation) {
		return roomReservationRepository.save(roomReservation);
	}
	
	public RoomReservation findRoomReservation(Long roomId) {
		return roomReservationRepository.getOne(roomId);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void removeRoomReservation(Long id) {
		System.out.println("STIGLO: " + id);
		RoomReservation rr = this.findRoomReservation(id);
		System.out.println("STIGLO: " + rr.getId());
		this.roomReservationRepository.delete(rr);
	}
}








