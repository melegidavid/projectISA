package ftn.isa.dto;

import ftn.isa.enums.Role;
import ftn.isa.model.User;

public class UserDTO {

	private Long id;
	private String username;
	private String password;
	private String email;
	private String name;
	private String lastName;
	private String city;
	private String telephoneNumber;
	private Role role;
	
	public UserDTO() {}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.name = user.getName();
		this.lastName = user.getLastName();
		this.city = user.getCity();
		this.telephoneNumber = user.getTelephoneNumber();
		
		switch(user.getAuthorities().get(0).getAuthority()) {
		
		  case "REGISTERED_USER": {
			  this.role = Role.REGISTERED_USER;
			  break;
		  }
		  case "SERVICE_ADMIN": {
			  this.role = Role.SERVICE_ADMIN;
			  break;
		  }
		  case "AVIO_COMPANY_ADMIN": {
			  this.role = Role.AVIO_COMPANY_ADMIN;
			  break;
		  }
		  case "HOTEL_ADMIN": {
			  this.role = Role.HOTEL_ADMIN;
			  break;
		  }
		  case "RENT_CAR_ADMIN": {
			  this.role = Role.RENT_CAR_ADMIN;
			  break;
		  }
		}
		
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
