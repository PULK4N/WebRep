package cinema.cinema.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Score implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	private Movie watchedMovie;
    @ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
    private Viewer viewer;
    @Column
	private Integer value;


	public Score(Movie watchedMovie, Viewer viewer, Integer value) {
		this.watchedMovie = watchedMovie;
		this.viewer = viewer;
		this.value = value;
	}


	public Score() {
		super();
	}

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Movie getWatchedMovie() {
		return this.watchedMovie;
	}
	public void setWatchedMovie(Movie watchedMovie) {
		this.watchedMovie = watchedMovie;
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

	@Override
	public String toString() {
		return "Score{" +
			" id=" + getId() +
			", watchedMovie=" + getWatchedMovie() +
			", viewer=" + getViewer() +
			", value=" + getValue() +
			"}";
	}

}
