package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.User;
import ftn.isa.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		List<User> list = new ArrayList<User>();
		userRepository.findAll().forEach(list::add);
		return list;
	}
	
	public User findUser(Long id) {
		return userRepository.getOne(id);
	}
	
	public void addUser(User user) {
		System.out.println("dodajeee");
		userRepository.save(user);
	}
	
	public void removeUser(User user) {
		userRepository.delete(user);
	}
	
	public void updateUser(User user) {
		userRepository.save(user);
	}
	
	
	public boolean isRegistered(String username) {
		User user = userRepository.findByUsername(username);
		if(user != null) {
			return true;
		} else {
			return false;
		}
	}
	
	// prilikom logvanja treba da je registrovan i da je aktiviran account
	
}
