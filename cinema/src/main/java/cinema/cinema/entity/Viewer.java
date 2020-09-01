package cinema.cinema.entity;

import java.util.*;
import javax.persistence.*;
import javax.swing.text.View;

@Entity
public class Viewer extends User{

	@OneToMany(mappedBy = "viewer",cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	private Set<Score> WatchedMoviesWithScores = new HashSet<>();
	@ManyToMany(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
    @JoinTable(name = "reserving",
    		joinColumns = @JoinColumn(name = "viewer_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "projection_id", referencedColumnName = "id"))
	private Set<Projection> ReservedCardsForMovies = new HashSet<>();

	public Set<Score> getWatchedMoviesWithScores() {
		return WatchedMoviesWithScores;
	}
	public void setWatchedMoviesWithScores(Set<Score> watchedMoviesWithScores) {
		WatchedMoviesWithScores = watchedMoviesWithScores;
	}

	public Set<Projection> getReservedCardsForMovies() {
		return ReservedCardsForMovies;
	}
	public void setReservedCardsForMovies(Set<Projection> reservedCardsForMovies) {
		ReservedCardsForMovies = reservedCardsForMovies;
	}

	public Viewer() {
		super();
	}

	public Viewer(Viewer viewer) {
		this.id = viewer.id;
		this.username = viewer.username;
		this.password = viewer.password;
		this.firstName = viewer.firstName;
		this.lastName = viewer.lastName;
		this.phoneNumber = viewer.phoneNumber;
		this.email = viewer.email;
		this.dateOfBirth = viewer.dateOfBirth;
		this.role = viewer.role;
	}

	public Viewer(User user) {
		this.id = user.id;
		this.username = user.username;
		this.password = user.password;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.phoneNumber = user.phoneNumber;
		this.email = user.email;
		this.dateOfBirth = user.dateOfBirth;
		this.role = user.role;
		this.active = true;
	}


	public Viewer(Long id, String username, String password, String firstName, String lastName, String phoneNumber, String email, String dateOfBirth, Role role) {
		super(username,password,firstName,lastName,phoneNumber,email,dateOfBirth,role, true);
		this.id = id;
	}

	// @Override
	// public String toString() {	
	// 	return "Viewer{" +
	// 		super.toString() +
	// 		"}";
	// }


}