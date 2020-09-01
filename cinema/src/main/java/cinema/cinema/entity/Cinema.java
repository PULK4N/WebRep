package cinema.cinema.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import cinema.cinema.entity.dto.CinemaDTO;

@Entity
public class Cinema implements Serializable {
	public Cinema(){
		
	}

	public Cinema(Cinema cinema) throws Exception{
		this.id = cinema.getId();
		this.name = cinema.getName();
		this.address = cinema.getAddress();
		this.phoneNumber = cinema.getPhoneNumber();
		this.email = cinema.getEmail();
		this.manager = cinema.getManager();
	}

	public Cinema(CinemaDTO cinemaDTO,Manager manager) {
		this.name = cinemaDTO.getName();
		this.address = cinemaDTO.getAddress();
		this.phoneNumber = cinemaDTO.getPhoneNumber();
		this.email = cinemaDTO.getEmail();
		this.manager = manager;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="cinema_name")
    private String name;
    
    @Column
    private String address;
    
    @Column
    private String phoneNumber;
    
    @Column
	private String email;
	
    @ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	private Manager manager;//to do
	
    @OneToMany(mappedBy = "cinema",cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
    private Set<Auditorium> auditoriums = new HashSet<>();; //lista sala, 
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Set<Auditorium> getAuditoriums() {
		return auditoriums;
	}
	public void setAuditoriums(Set<Auditorium> auditoriums) {
		this.auditoriums = auditoriums;
	}

	// @Override
	// public String toString() {
	// 	return "Cinema{" +
	// 		" id=" + getId() + 
	// 		", name='" + getName() + '\'' +
	// 		", address='" + getAddress() + '\'' +
	// 		", phoneNumber='" + getPhoneNumber() + '\'' +
	// 		", email='" + getEmail() + '\'' +
	// 		", manager=" + getManager() + 
	// 		"}";
	// }
    
}