package cinema.cinema.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import cinema.cinema.entity.dto.ProjectionDTO;

@Entity
public class Projection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column
	private Integer price;

    @Column
	private String scheduledTime;

    @Column
	private Integer reservedCards;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	private Movie movie;//
    @ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
    private Auditorium auditorium;//One auditorium has many projections
    @ManyToMany(mappedBy="ReservedCardsForMovies",cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	private Set<Viewer> viewers = new HashSet<>();//reservedCards = viewers.count();
	
	public Projection() {
		super();
	}

	public Projection(ProjectionDTO projectionDTO) {
		this.price = projectionDTO.getPrice();
		this.scheduledTime = projectionDTO.getScheduledTime();
		this.reservedCards = projectionDTO.getReservedCards();
	}


	public Projection(Long id, Integer price, String scheduledTime, Integer reservedCards, Movie movie, Auditorium auditorium, Set<Viewer> viewers) {
		this.id = id;
		this.price = price;
		this.scheduledTime = scheduledTime;
		this.reservedCards = reservedCards;
		this.movie = movie;
		this.auditorium = auditorium;
		this.viewers = viewers;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getScheduledTime() {
		return scheduledTime;
	}
	public void setScheduledTime(String scheduledTime) {
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

	@Override
	public String toString(){
		return "Projection{" +
		"id=" + id +
		", price=" + price + 
		", scheduledTime='" + scheduledTime + '\'' +
		", reservedCards=" + reservedCards + 
		", movie=" + movie + 
		", auditorium=" + auditorium + 
		'}';
	}
    
}