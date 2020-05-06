package cinema.cinema.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class Viewer extends User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@OneToMany
	private Set<Score> WatchedMoviesWithScores = new HashSet<>();
	@ManyToMany
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

}