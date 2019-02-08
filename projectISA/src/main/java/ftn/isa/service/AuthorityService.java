package ftn.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.enums.Role;
import ftn.isa.model.Authority;
import ftn.isa.repository.AuthorityRepository;

@Service
@Transactional(readOnly = true)
public class AuthorityService {
	@Autowired
	private AuthorityRepository authorityRepository;
	
	public Authority findOne(Long id) {
		return authorityRepository.getOne(id);
	}
	
	public Authority findByRole(Role role) {
		
		return authorityRepository.findOneByName(role.toString());
	}
}
