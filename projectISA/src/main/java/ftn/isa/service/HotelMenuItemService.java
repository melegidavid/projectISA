package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.HotelMenuItem;
import ftn.isa.model.HotelMenuItemReservation;
import ftn.isa.repository.HotelMenuItemRepository;
import ftn.isa.repository.HotelMenuItemReservationRepository;

@Service
public class HotelMenuItemService {

	@Autowired
	private HotelMenuItemRepository hotelMenuItemRepository;
	
	@Autowired
	private HotelMenuItemReservationRepository hotelMenuItemReservationRepository;
	
	public List<HotelMenuItem> getHotelMenuItems() {
		List<HotelMenuItem> list = new ArrayList<HotelMenuItem>();
		hotelMenuItemRepository.findAll().forEach(list::add);
		return list;
	}
	
	public HotelMenuItem findHotelMenuItem(Long id) {
		return hotelMenuItemRepository.getOne(id);
	}
	
	public HotelMenuItem saveHotelMenuItem(HotelMenuItem hotelMenuItem) {
		return hotelMenuItemRepository.save(hotelMenuItem);
	}
	
	public HotelMenuItem updateHotelMenuItem(HotelMenuItem hotelMenuItem) {
		return hotelMenuItemRepository.save(hotelMenuItem);
	}
	
	public void removeHotelMenuItem(HotelMenuItem hotelMenuItem) {
		hotelMenuItemRepository.delete(hotelMenuItem);
	}
	
	public HotelMenuItemReservation makeReservation(HotelMenuItemReservation hotelMenuItemReservation) {
		return hotelMenuItemReservationRepository.save(hotelMenuItemReservation);
	}

}
