package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.model.RentCarMenuItem;
import ftn.isa.repository.RentCarMenuItemRepository;

@Service
@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = false)
	public RentCarMenuItem save(RentCarMenuItem menuItem) {
		return rentCarMenuItemRepository.save(menuItem);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
	public RentCarMenuItem update(RentCarMenuItem menuItem) {
		return rentCarMenuItemRepository.save(menuItem);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		rentCarMenuItemRepository.deleteById(id);
	}

}
