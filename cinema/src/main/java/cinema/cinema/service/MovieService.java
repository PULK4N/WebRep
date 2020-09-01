package cinema.cinema.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cinema.cinema.entity.Movie;

@Service
public interface MovieService {
	public Movie findByName(String name);

	public List<Movie> findAll();

	public Movie findOne(Long id);

	public void delete(Long id);

	void save(Movie movie);
}