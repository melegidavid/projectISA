package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ftn.isa.model.User;
import ftn.isa.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	protected final Log LOGGER = LogFactory.getLog(getClass());
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
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

	// Funkcija koja na osnovu username-a iz baze vraca objekat User-a
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return user;
	    }
	}
	
	//temp metoda za getovanje usera preko username
	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			return null;
		}else {
			return user;
		}
	}
	
	// Funkcija pomocu koje korisnik menja svoju lozinku
	public void changePassword(String oldPassword, String newPassword) {

		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();

		if (authenticationManager != null) {
			LOGGER.debug("Re-authenticating user '" + username + "' for password change request.");

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
		} else {
			LOGGER.debug("No authentication manager set. can't change Password!");

			return;
		}

		LOGGER.debug("Changing password for user '" + username + "'");

		User user = (User) loadUserByUsername(username);

		// pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
		// ne zelimo da u bazi cuvamo lozinke u plain text formatu
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);

	}
	
	
	// prilikom logvanja treba da je registrovan i da je aktiviran account
	
}
