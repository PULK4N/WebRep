package cinema.cinema.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "movie_name")
    private String name;
    
    @Column
    private String description;
   
    @Column
    private String genre;
    
    @Column
    private Integer lenght;
    
    @Column
    private Float grade;//nije ista klasa kao score, jer score se vezuje za gledaoca
    
    @OneToMany
    private List<Score> scores;
    
    //jedan film se prikazuje u vise projekcija
    @OneToMany(mappedBy="movie")
    private List<Projection> projections;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getLenght() {
		return lenght;
	}

	public void setLenght(Integer lenght) {
		this.lenght = lenght;
	}

	public Float getGrade() {
		return grade;
	}

	public void setGrade(Float grade) {
		this.grade = grade;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public List<Projection> getProjections() {
		return projections;
	}

	public void setProjections(List<Projection> projections) {
		this.projections = projections;
	}

    
}
