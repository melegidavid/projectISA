package ftn.isa.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.auth.LoginRequest;
import ftn.isa.auth.TokenUtils;
import ftn.isa.model.User;
import ftn.isa.model.UserTokenState;
import ftn.isa.service.UserService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/auth")
public class LoginController {

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody LoginRequest loginRequest, HttpServletResponse resp) throws AuthenticationException, IOException {

		final Authentication authentication = authenticationManager.authenticate
				(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		// Ubaci username + password u kontext
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// Kreiraj token
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();

		System.out.println("LOGIN BEKEND");
		System.out.println(jwt);
		System.out.println(expiresIn);
		
		
		// Vrati token kao odgovor na uspesno autentifikaciju
		return new ResponseEntity<>(new UserTokenState(jwt, expiresIn), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<?> logout(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws AuthenticationException, IOException {

		// Vrati token kao odgovor na uspesno autentifikaciju
		return null;
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
	    User user = (User) this.userService.loadUserByUsername(username);

		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = tokenUtils.refreshToken(token);
			int expiresIn = tokenUtils.getExpiredIn();

			return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.badRequest().body(userTokenState);
		}
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		userService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}

	
}