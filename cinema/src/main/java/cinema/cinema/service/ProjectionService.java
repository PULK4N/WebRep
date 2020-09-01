package cinema.cinema.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cinema.cinema.entity.Projection;

@Service
public interface ProjectionService {
	public List<Projection> findAll();

	public Projection create(Projection projection) throws Exception;

	void delete(Long id);

	public Projection findOne(Long id);

	void save(Projection projection);

	// public Projection findByName(String name);
}