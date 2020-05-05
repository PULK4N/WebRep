package cinema.cinema.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class Projection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column
	private Integer price;

    @Column
	private java.time.LocalDateTime scheduledTime;

    @Column
	private Integer reservedCards;

    @ManyToOne
	private Movie movie;//
    @ManyToOne
    private Auditorium auditorium;//One auditorium has many projections
    @ManyToMany(mappedBy="ReservedCardsForMovies")
    private Set<Viewer> viewers;//reservedCards = viewers.count();

	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public java.time.LocalDateTime getScheduledTime() {
		return scheduledTime;
	}
	public void setScheduledTime(java.time.LocalDateTime scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	public Integer getReservedCards() {
		return reservedCards;
	}
	public void setReservedCards(Integer reservedCards) {
		this.reservedCards = reservedCards;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Auditorium getAuditorium() {
		return auditorium;
	}
	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}
	public Set<Viewer> getViewers() {
		return viewers;
	}
	public void setViewers(Set<Viewer> viewers) {
		this.viewers = viewers;
	}


    
}