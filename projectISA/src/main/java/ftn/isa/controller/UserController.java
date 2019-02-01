package ftn.isa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.dto.UserDTO;
import ftn.isa.model.Friendship;
import ftn.isa.model.User;
import ftn.isa.service.AuthorityService;
import ftn.isa.service.FriendshipService;
import ftn.isa.service.NotificationService;
import ftn.isa.service.UserService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private FriendshipService friendshipService;
	
	@Autowired
	private NotificationService mailService;


	public ResponseEntity<List<UserDTO>> getUsers() {
		
		List<User> users = userService.getAllUsers();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		
		for(User u : users) {
			userDTOs.add(new UserDTO(u));
		}
		
		return new ResponseEntity<>(userDTOs, HttpStatus.OK);
	}
	
	// da bude za admina
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
		// morace rugacije kad se priatelji uvedu, preko dto-a
		User user = new User();
		
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setLastName(userDTO.getLastName());
		user.setCity(userDTO.getCity());
		user.setTelephoneNumber(userDTO.getTelephoneNumber());
		user.setRole(userDTO.getRole());
		
		userService.addUser(user);
		System.out.println("DODAO KORISNIKA");
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> registerUser(@RequestBody UserDTO userDTO) {
		// na frontu validacija prosledjenih podataka

		System.out.println("usao u kontroler bekenda");
		boolean alreadyRegistered = userService.isRegistered(userDTO.getUsername());
		if (alreadyRegistered) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getName(),
				userDTO.getLastName(), userDTO.getCity(), userDTO.getTelephoneNumber(), userDTO.getRole(), false);
		
		//user.setActivatedAccount(true); //posle brisanje ovog, pa preko maila
		user.setActivatedAccount(false);
		user.getAuthorities().add(authorityService.findOne((long) 1));
		userService.addUser(user);

		try {
			mailService.sendNotification(user); //send mail	
		} catch (MailException m) {
			System.out.println("Neuspesno poslata poruka");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/activate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> activateUser(@RequestBody UserDTO userDTO) {

		boolean registered = userService.isRegistered(userDTO.getUsername());

		if (!registered) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User user = userService.findUser(userDTO.getId());
		user.setActivatedAccount(true);
		userService.updateUser(user);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	// temp metoda za getovanje usera preko username
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {
		if (username != null) {
			User user = userService.getUserByUsername(username);
			if (user == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// get friends of user
	@RequestMapping(value = "/{username}/friendsList", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getFriendsOfUserByUsername(@PathVariable("username") String username) {
		// user koji je otvorio listu svojih prijatelja
		if (username != null) {
			User user = userService.getUserByUsername(username);
			// lista svih prijateljstava
			List<Friendship> friendshipList = friendshipService.getAllFriendships();
			// lista prijatelja
			List<UserDTO> users = new ArrayList<UserDTO>();
			if (friendshipList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				for (Friendship friendship : friendshipList) {
					// provera da li prosledjen prijatelj postoji u prijateljstvu i da li je to
					// prijateljstvo potvrdjeno
					if (friendship.getUser1().equals(user) && friendship.isAccepted()) {
						users.add(new UserDTO(friendship.getUser2()));
					}
				}

				return new ResponseEntity<>(users, HttpStatus.OK);

			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// get usersToSearch
	@RequestMapping(value = "/{username}/usersToSearch", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsersToSearch(@PathVariable("username") String username) {
		if (username != null) {
			User user = userService.getUserByUsername(username);

			List<User> users = new ArrayList<User>();
			users = userService.getAllUsers();

			List<Friendship> friendships = new ArrayList<Friendship>();
			friendships = friendshipService.getAllFriendships();

			List<User> friends = new ArrayList<User>();

			for (Friendship friendship : friendships) {
				if (friendship.getUser1().getId().equals(user.getId()) && friendship.isAccepted()) {
					friends.add(friendship.getUser2());
				}
			}

			if (users.contains(user)) {
				users.remove(user);
			}
			List<User> tempList = users;

			for (User friend : friends) {
				if (users.contains(friend)) {
					tempList.remove(friend);
				}
			}
			List<UserDTO> usersDTO = new ArrayList<UserDTO>();

			if (!tempList.isEmpty()) {
				for (User us : tempList) {
					usersDTO.add(new UserDTO(us));
				}
				return new ResponseEntity<>(usersDTO, HttpStatus.OK);
			}

			return new ResponseEntity<>(usersDTO, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// get friends of user
	@RequestMapping(value = "/{id}/friends", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getFriendsOfUser(@PathVariable("id") Long id) {
		// user koji je otvorio listu svojih prijatelja
		if (id != null) {
			User user = userService.findUser(id);
			// lista svih prijateljstava
			List<Friendship> friendshipList = friendshipService.getAllFriendships();
			// lista prijatelja
			List<UserDTO> users = new ArrayList<UserDTO>();
			if (friendshipList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				for (Friendship friendship : friendshipList) {
					// provera da li prosledjen prijatelj postoji u prijateljstvu i da li je to
					// prijateljstvo potvrdjeno
					if (friendship.getUser1().equals(user) && friendship.isAccepted()) {
						users.add(new UserDTO(friendship.getUser2()));
					}
				}
				return new ResponseEntity<>(users, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	// remove friend from list
	@RequestMapping(value = "/{id}/friends/{idFriend}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeFriendFromlist(@PathVariable("id") Long id,
			@PathVariable("idFriend") Long idFriend) {

		if (id != null && idFriend != null) {
			User user = userService.findUser(id);
			User friend = userService.findUser(idFriend);
			boolean friendship1 = false;
			boolean friendship2 = false;
			// lista svih prijateljstava
			List<Friendship> friendshipList = friendshipService.getAllFriendships();

			for (Friendship friendship : friendshipList) {
				if (friendship.getUser1().equals(user) && friendship.getUser2().equals(friend)
						&& friendship.isAccepted()) {
					friendship1 = true;
					friendshipService.removeFriendship(friendship.getId());
				}
				if (friendship.getUser1().equals(friend) && friendship.getUser2().equals(user)
						&& friendship.isAccepted()) {
					friendship2 = true;
					friendshipService.removeFriendship(friendship.getId());
				}
			}
			if (friendship1 && friendship2) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
