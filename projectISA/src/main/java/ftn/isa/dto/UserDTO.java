package ftn.isa.dto;

import ftn.isa.enums.Role;

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
	//private Object adminOf;
	
	public UserDTO() {}

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
/*
	public Object getAdminOf() {
		return adminOf;
	}

	public void setAdminOf(Object adminOf) {
		this.adminOf = adminOf;
	}
	*/
	
	
}
