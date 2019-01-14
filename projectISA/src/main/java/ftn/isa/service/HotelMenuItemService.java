package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ftn.isa.model.HotelMenuItem;
import ftn.isa.repository.HotelMenuItemRepository;

public class HotelMenuItemService {

	@Autowired
	private HotelMenuItemRepository hotelMenuItemRepository;
	
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

}
