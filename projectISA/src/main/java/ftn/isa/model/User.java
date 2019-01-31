package ftn.isa.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ftn.isa.enums.Role;

@Entity
public class User implements UserDetails {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private String password;
	private String email;
	private String name;
	private String lastName;
	private String city;
	private String telephoneNumber;
	private Role role; 
	private boolean activatedAccount;
	
	//private Object adminOf; //za sad ovako, napraviti mozda posebnu tabelu koja mapira admine na ono za sta su admini
	
	@Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;
	
	private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<VehicleReservation> vehicleReservations = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<RoomReservation> roomReservations = new ArrayList<>();
	
    
	public User() {}
	
	public User(String username, String password, String email, String name, String lastName, String city,
			String telephoneNumber, Role role, boolean activated) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.city = city;
		this.telephoneNumber = telephoneNumber;
		this.role = role;
		this.activatedAccount = activated;
		//this.adminOf = null; // za sad
		
		this.enabled = true;
		this.lastPasswordResetDate = Timestamp.valueOf(LocalDateTime.now());
		this.authorities = new ArrayList<Authority>();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
		Timestamp now = new Timestamp(DateTime.now().getMillis());
        this.setLastPasswordResetDate( now );
        this.password = password;
	}
	
    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public List<Authority> getAuthorities() {
        return this.authorities;
    }
    
    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
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
/*
	public Object getAdminOf() {
		return adminOf;
	}

	public void setAdminOf(Object adminOf) {
		this.adminOf = adminOf;
	}*/

	public boolean isActivatedAccount() {
		return activatedAccount;
	}

	public void setActivatedAccount(boolean activatedAccount) {
		this.activatedAccount = activatedAccount;
	}

	
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
	
	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	
	public List<VehicleReservation> getVehicleReservations() {
		return vehicleReservations;
	}

	public void setVehicleReservations(List<VehicleReservation> vehicleReservations) {
		this.vehicleReservations = vehicleReservations;
	}

	public List<RoomReservation> getRoomReservations() {
		return roomReservations;
	}

	public void setRoomReservations(List<RoomReservation> roomReservations) {
		this.roomReservations = roomReservations;
	}

	
}
