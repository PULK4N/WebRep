package cinema.cinema.entity;

import java.io.Serializable;
import javax.persistence.*;

@MappedSuperclass
public class User implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}  

    @Column
	protected String username;

	@Column 
	protected String password;

    @Column
	protected String firstName;
    
    @Column
	protected String lastName;
    
    @Column
	protected String phoneNumber;
    
    @Column
	protected String email;
    
    @Column
	protected String dateOfBirth;
    
    @Column
	protected Role role;

	@Column
	protected Boolean active;

	public User() {
	}

	public User(User user) {
		this.username = user.username;
		this.password = user.password;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.phoneNumber = user.phoneNumber;
		this.email = user.email;
		this.dateOfBirth = user.dateOfBirth;
		this.role = user.role;
		this.active = user.active;
	}

	public User(String username, String password, String firstName, String lastName, String phoneNumber, String email, String dateOfBirth, Role role, Boolean active){
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
		this.active = active;
	}



	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Boolean getActive(){
		return active;
	}

	public void setActive(Boolean active){
		this.active = active;
	}

	// @Override
	// public String toString() {
	// 	return 
	// 		"id=" + getId().toString() + '\'' +
	// 		", username='" + getUsername() + '\'' +
	// 		", password='" + getPassword() + '\'' +
	// 		", firstName='" + getFirstName() + '\'' +
	// 		", lastName='" + getLastName() + '\'' +
	// 		", phoneNumber='" + getPhoneNumber() + '\'' +
	// 		", email='" + getEmail() + '\'' +
	// 		", dateOfBirth='" + getDateOfBirth() + '\'' +
	// 		", role='" + getRole().toString() + '\'' +
	// 		", active='" + getActive().toString() + '\'';
	// }

}
