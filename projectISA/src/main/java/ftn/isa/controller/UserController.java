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

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers() {

		List<User> users = userService.getAllUsers();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();

		for (User u : users) {
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

	// update user
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
		User user = userService.findUser(id);

		user.setName(userDTO.getName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setTelephoneNumber(userDTO.getTelephoneNumber());
		user.setCity(userDTO.getCity());

		user = userService.updateUser(user);

		UserDTO updated = new UserDTO(user);
		return new ResponseEntity<>(updated, HttpStatus.OK);
		
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

		user.setEnabled(false);
		user.getAuthorities().add(authorityService.findOne((long) 1));
		userService.addUser(user);

		try {
			mailService.sendNotification(user); // send mail
		} catch (MailException m) {
			System.out.println("Neuspesno poslata poruka");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/activate/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> activateUser(@PathVariable("id") Long id) {
		System.out.println("PROSLEDJEN ID JE: " + id);
		User user = userService.findUser(id);

		boolean registered = userService.isRegistered(user.getUsername());
		System.out.println("KORISNIK VEC REGISTROVAN? :" + registered);
		if (!registered) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}

		user.setEnabled(true);
		userService.updateUser(user);

		return new ResponseEntity<>(true, HttpStatus.OK);
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
				} else if (friendship.getUser2().getId().equals(user.getId()) && friendship.isAccepted() == false) {
					friends.add(friendship.getUser1());
				} else if (friendship.getUser1().getId().equals(user.getId()) && (friendship.isAccepted() == false)) {
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

	// metoda za dodavanje prijatelja
	@RequestMapping(value = "/{id}/addFriend", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> addFriend(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {

		Friendship newFriendship = new Friendship();

		List<Friendship> friendships = friendshipService.getAllFriendships();

		boolean exist = false;

		for (Friendship friendship : friendships) {
			if (friendship.getUser1().getId().equals(id) && friendship.getUser2().getId().equals(userDTO.getId())) {
				exist = true;
			}
		}

		if (exist) {

		} else {
			User user1 = userService.findUser(id);
			User user2 = new User();
			boolean accepted = false;

			user2.setId(userDTO.getId());
			user2.setUsername(userDTO.getUsername());
			user2.setPassword(userDTO.getPassword());
			user2.setEmail(userDTO.getEmail());
			user2.setName(userDTO.getName());
			user2.setLastName(userDTO.getLastName());
			user2.setCity(userDTO.getCity());
			user2.setTelephoneNumber(userDTO.getTelephoneNumber());
			user2.setRole(userDTO.getRole());

			newFriendship.setUser1(user1);
			newFriendship.setUser2(user2);
			newFriendship.setAccepted(accepted);

			newFriendship = friendshipService.saveFriendship(newFriendship);
		}

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{username}/listOfRequests", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getListOfRequests(@PathVariable("username") String username) {

		User user = userService.getUserByUsername(username);

		List<Friendship> friendships = friendshipService.getAllFriendships();

		List<UserDTO> requests = new ArrayList<UserDTO>();

		if (friendships.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for (Friendship friendship : friendships) {
				if (friendship.getUser2().equals(user) && friendship.isAccepted() == false) {
					requests.add(new UserDTO(friendship.getUser1()));
				}
			}
			return new ResponseEntity<>(requests, HttpStatus.OK);
		}
	}

	// metoda za prikaz poslatih zahteva
	@RequestMapping(value = "/{id}/acceptFriendshipRequest/{idFriend}", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> acceptFriendshipRequest(@PathVariable("id") Long id,
			@PathVariable("idFriend") Long idFriend) {
		User user2 = userService.findUser(id);
		User user1 = userService.findUser(idFriend);
		List<Friendship> friendships = friendshipService.getAllFriendships();

		// promena torke onog ko nam je poslao zahtev
		for (Friendship friendship : friendships) {
			if (friendship.getUser2().getId().equals(id) && friendship.isAccepted() == false
					&& friendship.getUser1().getId().equals(idFriend)) {
				friendship.setAccepted(true);

				friendship = friendshipService.saveFriendship(friendship);

				// pravljenje nove torke
				Friendship newFriendship = new Friendship();
				newFriendship.setUser1(user2);
				newFriendship.setUser2(user1);
				newFriendship.setAccepted(true);

				newFriendship = friendshipService.saveFriendship(newFriendship);
				return new ResponseEntity<>(new UserDTO(user1), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}/declineFriendshipRequest/{idFriend}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> declineFriendshipRequest(@PathVariable("id") Long id,
			@PathVariable("idFriend") Long idFriend) {

		List<Friendship> friendships = friendshipService.getAllFriendships();

		for (Friendship friendship : friendships) {
			if (friendship.getUser1().getId().equals(idFriend) && friendship.getUser2().getId().equals(id)
					&& friendship.isAccepted() == false) {
				friendshipService.removeFriendship(friendship.getId());
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
