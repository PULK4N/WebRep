package cinema.cinema.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
public class Cinema implements Serializable {
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
    @OneToOne
    private Manager manager;//to do
    @OneToMany(mappedBy = "cinema")
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

    
}