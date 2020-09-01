package cinema.cinema.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cinema.cinema.entity.Auditorium;

@Service
public interface AuditoriumService {

	Auditorium create(Auditorium auditorium) throws Exception;

	public List<Auditorium> findAll();

	public Auditorium findOne(Long id);

	public void delete(Long id);

	void save(Auditorium auditorium);
}