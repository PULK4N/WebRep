package cinema.cinema.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
public class Auditorium implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer capacity;
    @OneToMany(mappedBy = "auditorium")
    private Set<Projection> schedule = new HashSet<>();
    @ManyToOne
    private Cinema cinema;
    
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

    
}