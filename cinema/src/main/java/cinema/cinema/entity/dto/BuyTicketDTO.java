package cinema.cinema.entity.dto;

public class BuyTicketDTO {
	String username;	
	String password;
	String movieName;	
	Long projectionId;


	public BuyTicketDTO(String username, String password, String movieName, Long projectionId) {
		this.username = username;
		this.password = password;
		this.movieName = movieName;
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

	public String getMovieName() {
		return this.movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Long getProjectionId() {
		return this.projectionId;
	}

	public void setProjectionId(Long projectionId) {
		this.projectionId = projectionId;
	}

}