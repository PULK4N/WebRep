package cinema.cinema.entity.dto;

import cinema.cinema.entity.Role;

public class ManagerDTO extends UserDTO {

	String cinemaName;

	public ManagerDTO(long id, String username, String password, String firstName, String lastName, String phoneNumber, String email, String dateOfBirth, Role role, Boolean active, String cinemaName) {
		super(id, username, password, firstName, lastName, phoneNumber, email, dateOfBirth, role, active);
		this.cinemaName = cinemaName;
	}

	public ManagerDTO(long id, String username, String password, String firstName, String lastName, String phoneNumber, String email, String dateOfBirth, Role role, Boolean active) {
		super(id, username, password, firstName, lastName, phoneNumber, email, dateOfBirth, role, active);
		this.cinemaName = "unAttributedCinemaName";
	}


	public String getCinemaName() {
		return this.cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	
}