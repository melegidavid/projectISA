package ftn.isa.dto;

public class ReservationDTO {
	
	private String username;  //username korisnika koji vrsi rezervaciju
	private Long id;  //id onog sta treba da se rezervise
	
	private Long returnPlaceId;
	private Long takePlaceId;
	
	public ReservationDTO() {
		
	}
	
	public ReservationDTO(String username, Long id) {
		this.id = id;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReturnPlaceId() {
		return returnPlaceId;
	}

	public void setReturnPlaceId(Long returnPlaceId) {
		this.returnPlaceId = returnPlaceId;
	}

	public Long getTakePlaceId() {
		return takePlaceId;
	}

	public void setTakePlaceId(Long takePlaceId) {
		this.takePlaceId = takePlaceId;
	}
	
}
