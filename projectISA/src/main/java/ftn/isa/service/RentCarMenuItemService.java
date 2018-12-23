package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftn.isa.model.RentCarMenuItem;
import ftn.isa.repository.RentCarMenuItemRepository;

@Service
public class RentCarMenuItemService {
	
	@Autowired
	private RentCarMenuItemRepository rentCarMenuItemRepository;
	
	
	
	public RentCarMenuItem findOne(Long id) {
		return rentCarMenuItemRepository.getOne(id);
	}
	
	public List<RentCarMenuItem> findAll() {
		List<RentCarMenuItem> list = new ArrayList<RentCarMenuItem>();
		rentCarMenuItemRepository.findAll().forEach(list::add);
		return list;
	}
	
	public RentCarMenuItem save(RentCarMenuItem menuItem) {
		return rentCarMenuItemRepository.save(menuItem);
	}
	
	public RentCarMenuItem update(RentCarMenuItem menuItem) {
		return rentCarMenuItemRepository.save(menuItem);
	}
	
	public void remove(Long id) {
		rentCarMenuItemRepository.deleteById(id);
	}

}
