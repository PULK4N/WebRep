package cinema.cinema.entity.dto;

import cinema.cinema.entity.Projection;

public class ProjectionDTO {

    private Long id;
	private Integer price;
	private String scheduledTime;
	private Integer reservedCards;
	private String movieName;
	private String movieGenre;
	private String movieDescription;
	private Float movieGrade;
	private Long auditoriumId;
	
	public ProjectionDTO() {
		super();
	}

	public ProjectionDTO(Projection projection) {
		this.id = projection.getId();
		this.price = projection.getPrice();
		this.scheduledTime = projection.getScheduledTime();
		this.reservedCards = projection.getReservedCards();
		this.movieName = projection.getMovie().getName();
		this.movieGenre = projection.getMovie().getGenre();
		this.movieDescription = projection.getMovie().getDescription();
		this.movieGrade = projection.getMovie().getGrade();
		this.auditoriumId = projection.getAuditorium().getId();
	}


	public ProjectionDTO(Long id, Integer price, String scheduledTime, Integer reservedCards, String movieName, String movieGenre, String movieDescription, Float movieGrade, Long auditoriumId) {
		this.id = id;
		this.price = price;
		this.scheduledTime = scheduledTime;
		this.reservedCards = reservedCards;
		this.movieName = movieName;
		this.movieGenre = movieGenre;
		this.movieDescription = movieDescription;
		this.movieGrade = movieGrade;
		this.auditoriumId = auditoriumId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getScheduledTime() {
		return this.scheduledTime;
	}

	public void setScheduledTime(String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public Integer getReservedCards() {
		return this.reservedCards;
	}

	public void setReservedCards(Integer reservedCards) {
		this.reservedCards = reservedCards;
	}

	public String getMovieName() {
		return this.movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieGenre() {
		return this.movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public String getMovieDescription() {
		return this.movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	public Long getAuditoriumId() {
		return this.auditoriumId;
	}

	public Float getMovieGrade() {
		return this.movieGrade;
	}

	public void setMovieGrade(Float movieGrade) {
		this.movieGrade = movieGrade;
	}

	public void setAuditoriumId(Long auditoriumId) {
		this.auditoriumId = auditoriumId;
	}

}