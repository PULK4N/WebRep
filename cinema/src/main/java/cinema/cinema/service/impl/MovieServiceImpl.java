package cinema.cinema.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cinema.cinema.entity.Movie;
import cinema.cinema.repository.MovieRepository;
import cinema.cinema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{
	@Autowired
	MovieRepository movieRepository;

	public List<Movie> findAll(){
		return this.movieRepository.findAll();
	}

	public Movie findOne(Long id){
		return this.movieRepository.getOne(id);
	}

	public void delete(Long id){
		this.movieRepository.deleteById(id);
	}

	public Movie findByName(String name){
		return this.movieRepository.findByName(name);
	}

    public void save(Movie movie){
        this.movieRepository.save(movie);
    }
}