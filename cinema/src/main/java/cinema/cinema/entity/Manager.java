package cinema.cinema.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Manager extends User implements Serializable {
	@OneToOne
	private Cinema cinema;

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	
}
