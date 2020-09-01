package cinema.cinema.entity.dto;

import cinema.cinema.entity.Movie;

public class MovieDTO {
	
	public MovieDTO() {
		super();
	}

	public MovieDTO(Movie movie) {
		this.id = movie.getId();
		this.name = movie.getName();
		this.description = movie.getDescription();
		this.genre = movie.getGenre();
		this.lenght = movie.getLenght();
		this.grade = movie.getGrade();
	}

	public MovieDTO(Long id, String name, String description, String genre, Integer lenght, Float grade) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.genre = genre;
		this.lenght = lenght;
		this.grade = grade;
	}

    private Long id;
    private String name;
    private String description;
    private String genre;
    private Integer lenght;
	private Float grade;
	

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getLenght() {
		return this.lenght;
	}

	public void setLenght(Integer lenght) {
		this.lenght = lenght;
	}

	public Float getGrade() {
		return this.grade;
	}

	public void setGrade(Float grade) {
		this.grade = grade;
	}




}