package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.RegisteredUser;
import ftn.isa.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<RegisteredUser> findAll() {
		List<RegisteredUser> lista = new ArrayList<RegisteredUser>();
		userRepository.findAll().forEach(lista::add);
		return lista;
		
		//return userRepository.findAll();
	}
	
	public RegisteredUser findUser(Long id) {
		return userRepository.getOne(id);
	}
	
	public void addUser(RegisteredUser user) {
		userRepository.save(user);
	}
	
	public void removeUser(RegisteredUser user) {
		userRepository.delete(user);
	}
	
	public void updateUser(RegisteredUser user) {
		userRepository.save(user);
	}
	
	
}
