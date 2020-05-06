package cinema.cinema.entity;

import javax.persistence.*;
import java.io.*;

@Entity
public class Administrator extends User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}  
}
