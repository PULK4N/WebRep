package cinema.cinema.entity.dto;

public class ReserveTicketDTO {
	private String username;	
	private String password;	
	private Long projectionId;


	public ReserveTicketDTO(String username, String password, Long projectionId) {
		this.username = username;
		this.password = password;
		this.projectionId = projectionId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getProjectionId() {
		return this.projectionId;
	}

	public void setProjectionId(Long projectionId) {
		this.projectionId = projectionId;
	}

}