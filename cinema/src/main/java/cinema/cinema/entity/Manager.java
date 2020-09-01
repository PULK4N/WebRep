package cinema.cinema.entity;


import javax.persistence.*;
import java.util.*;

@Entity
public class Manager extends User{

	
	@OneToMany(mappedBy = "manager",cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	private Set<Cinema> cinemas;

	public Set<Cinema> getCinemas() {
		return cinemas;
	}
	public void setCinemas(Cinema cinema) {
		this.cinemas = cinemas;
	}
	
	public Manager() {
		super();
	}

	public Manager(User manager) {
		super(manager);
	}

	public Manager(Manager manager) {
		super((User)manager);
		this.cinemas = manager.cinemas;
	}
	// @Override
	// public String toString() {
	// 	return "Manager{" +
	// 		super.toString() +
	// 		" cinema=" + getCinemas() +
	// 		"}";
	// }
}
