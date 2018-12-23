package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftn.isa.model.Address;
import ftn.isa.repository.AddressRepository;

@Service
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
	
	public Address save(Address address) {
		return addressRepository.save(address);
	}
	
	public Address update(Address address) {
		return addressRepository.save(address);
	}
	
	public void remove(Long id) {
		addressRepository.deleteById(id);
	}

}
