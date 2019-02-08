package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.model.Address;
import ftn.isa.repository.AddressRepository;

@Service
@Transactional(readOnly = true)
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address findOne(Long id) {
		return addressRepository.getOne(id);
	}
	
	public List<Address> findAll() {
		List<Address> list = new ArrayList<Address>();
		addressRepository.findAll().forEach(list::add);
		return list;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Address save(Address address) {
		return addressRepository.save(address);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
	public Address update(Address address) {
		return addressRepository.save(address);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		addressRepository.deleteById(id);
	}

}
