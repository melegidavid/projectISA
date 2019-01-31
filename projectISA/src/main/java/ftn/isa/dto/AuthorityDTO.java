package ftn.isa.dto;

import java.util.ArrayList;
import java.util.List;

import ftn.isa.model.Authority;

public class AuthorityDTO {
	
	private List<Authority> authorities;
	private String username;
	
	public AuthorityDTO() {}
	
	public AuthorityDTO(String username) {
		super();
		this.authorities = new ArrayList<>();
		this.username = username;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
