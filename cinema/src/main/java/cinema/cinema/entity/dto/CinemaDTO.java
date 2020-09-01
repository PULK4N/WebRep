package cinema.cinema.entity.dto;
import cinema.cinema.entity.Cinema;

public class CinemaDTO {

	private Long id;
	private String newName;
    private String name;
    private String address;
    private String phoneNumber;
	private String email;
	private String manager;

	public CinemaDTO() {
	}

	public CinemaDTO(Long id, String newName, String name, String address, String phoneNumber, String email, String manager) {
		this.id = id;
		this.newName = newName;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.manager = manager;
	}

	public CinemaDTO(Cinema cinema) throws Exception{
		this.id = cinema.getId();
		this.name = cinema.getName();
		this.address = cinema.getAddress();
		this.phoneNumber = cinema.getPhoneNumber();
		this.email = cinema.getEmail();
		if(cinema.getManager()!=null)
			this.manager = cinema.getManager().getUsername();
	}

	public String getNewName() {
		return this.newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}
	
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
    
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

	@Override
	public String toString() {
		return "Cinema{" +
			" id=" + getId() + 
			", name='" + getName() + '\'' +
			", address='" + getAddress() + '\'' +
			", phoneNumber='" + getPhoneNumber() + '\'' +
			", email='" + getEmail() + '\'' +
			"}";
	}

	
}