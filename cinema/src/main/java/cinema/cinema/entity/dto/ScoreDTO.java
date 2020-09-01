package cinema.cinema.entity.dto;

public class ScoreDTO {
	Long scoreId;
	String username;	
	String password;	
	String movieName;
	Integer movieScore;

	public ScoreDTO() {
	}

	public ScoreDTO(String movieName, Integer movieScore) {
		this.movieName = movieName;
		this.movieScore = movieScore;
	}

	public Long getScoreId() {
		return this.scoreId;
	}

	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
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

	public Integer getMovieScore() {
		return this.movieScore;
	}

	public void setMovieScore(Integer movieScore) {
		this.movieScore = movieScore;
	}

}