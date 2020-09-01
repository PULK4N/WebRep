package cinema.cinema.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import cinema.cinema.entity.dto.AuditoriumDTO;

@Entity
public class Auditorium implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer capacity;
    @OneToMany(mappedBy = "auditorium",cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
    private Set<Projection> schedule = new HashSet<>();
    @ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	private Cinema cinema;


	public Auditorium(Long id, Integer capacity, Set<Projection> schedule, Cinema cinema) {
		this.id = id;
		this.capacity = capacity;
		this.schedule = schedule;
		this.cinema = cinema;
	}

	
	public Auditorium(AuditoriumDTO auditoriumDTO) {
		this.capacity = auditoriumDTO.getCapacity();
	}

	public Auditorium() {
		super();
	}

    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Set<Projection> getSchedule() {
		return schedule;
	}
	public void setSchedule(Set<Projection> schedule) {
		this.schedule = schedule;
	}

	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	@Override
	public String toString() {
		return "Auditorium{" +
			" id=" + id + 
			", capacity=" + capacity +
			", cinema=" + cinema +
			"}";
	}	
    
}