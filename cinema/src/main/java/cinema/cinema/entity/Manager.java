package cinema.cinema.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Manager extends User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne
	private Cinema cinema;

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	

	public Long getId() {
		return id;
	}
	

	public void setId(Long id) {
		this.id = id;
	}

	
}
