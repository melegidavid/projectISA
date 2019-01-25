package ftn.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.dto.UserDTO;
import ftn.isa.model.User;
import ftn.isa.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public List<User> getUsers() {
		return userService.getAllUsers();
	}
	
	//da bude za admina
	@RequestMapping(value="/add",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addUser(@RequestBody User user) {
		//morace rugacije kad se priatelji uvedu, preko dto-a
		userService.addUser(user);
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> registerUser(@RequestBody UserDTO userDTO) {
		//na frontu validacija prosledjenih podataka

		System.out.println("usao u kontroler bekenda");
		boolean alreadyRegistered = userService.isRegistered(userDTO.getUsername());
		if(alreadyRegistered) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User user = new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getEmail(),userDTO.getName(),userDTO.getLastName(),userDTO.getCity(),userDTO.getTelephoneNumber(),userDTO.getRole(), false);
		user.setActivatedAccount(true); // posle brisanje ovog, pa preko maila
		userService.addUser(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/activate",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> activateUser(@RequestBody UserDTO userDTO) {
		
		boolean registered = userService.isRegistered(userDTO.getUsername());
		
		if(!registered) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User user = userService.findUser(userDTO.getId());
		user.setActivatedAccount(true);
		userService.updateUser(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	
}
