package ftn.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.model.RegisteredUser;
import ftn.isa.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/users",method = RequestMethod.GET)
	public List<RegisteredUser> getUsers() {
		return userService.findAll();
	}
	
	@RequestMapping(value="/users",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addUser(RegisteredUser user) {
		userService.addUser(user);
	}
	
	
}
