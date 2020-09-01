package cinema.cinema.entity;

import javax.persistence.*;


@Entity
public class Administrator extends User {

	public Administrator(){
		super();
	}

	public Administrator(User administrator) {
		super(administrator);
		this.active = false;
	}
	
	public Administrator(Administrator administrator) {
		super((User)administrator);
		this.active = false;
	}

	// @Override
	// public String toString() {
	// 	return "Administrator{"  +
	// 		super.toString() +
	// 		"}";
	// }
}
