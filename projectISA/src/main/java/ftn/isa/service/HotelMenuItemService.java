package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.model.HotelMenuItem;
import ftn.isa.model.HotelMenuItemReservation;
import ftn.isa.repository.HotelMenuItemRepository;
import ftn.isa.repository.HotelMenuItemReservationRepository;

@Service
@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public HotelMenuItem saveHotelMenuItem(HotelMenuItem hotelMenuItem) {
		return hotelMenuItemRepository.save(hotelMenuItem);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
	public HotelMenuItem updateHotelMenuItem(HotelMenuItem hotelMenuItem) {
		return hotelMenuItemRepository.save(hotelMenuItem);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void removeHotelMenuItem(HotelMenuItem hotelMenuItem) {
		HotelMenuItem item = this.findHotelMenuItem(hotelMenuItem.getId());
		item.setDeleted(true);
		this.updateHotelMenuItem(item);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public HotelMenuItemReservation makeReservation(HotelMenuItemReservation hotelMenuItemReservation) {
		return hotelMenuItemReservationRepository.save(hotelMenuItemReservation);
	}

}
