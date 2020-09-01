package cinema.cinema.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cinema.cinema.entity.Cinema;

@Service
public interface CinemaService {
	public List<Cinema> findAll();

	public Cinema create(Cinema cinema) throws Exception;

	void delete(Long id);

	public Cinema findByName(String name);

	void save(Cinema cinema);
}