package cinema.cinema.entity.dto;

import java.util.List;

import cinema.cinema.entity.Role;
import cinema.cinema.entity.Score;

public class ViewerDTO {
	long id;
	String username;	
	String password;	
	String firstName;
	String lastName;
	String phoneNumber;
	String email;
	String dateOfBirth;
	Role role;

	List<Score> WatchedMoviesWithScores;

	public ViewerDTO(long id, String username, String password, String firstName, String lastName, String phoneNumber, String email, String dateOfBirth, Role role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
	}

	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		return this.dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Role getRole() {
		return this.role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public List<Score> getWatchedMoviesWithScores() {
		return this.WatchedMoviesWithScores;
	}
	public void setWatchedMoviesWithScores(List<Score> WatchedMoviesWithScores) {
		this.WatchedMoviesWithScores = WatchedMoviesWithScores;
	}
	
}