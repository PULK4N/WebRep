package cinema.cinema.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Score implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
	private Movie watchedMovie;
    @ManyToOne
    private Viewer viewer;
    @Column
	private Integer value;
	public Movie getMovie() {
		return watchedMovie;
	}
	public void setMovie(Movie movie) {
		this.watchedMovie = movie;
	}
	public Viewer getViewer() {
		return viewer;
	}
	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}

}
