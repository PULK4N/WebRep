package cinema.cinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import cinema.cinema.entity.Cinema;
import cinema.cinema.repository.CinemaRepository;
import cinema.cinema.service.CinemaService;

@Service
public class CinemaServiceImpl implements CinemaService{

	@Autowired
	CinemaRepository cinemaRepository;

	@Override
	public List<Cinema> findAll(){
		return this.cinemaRepository.findAll();
	}

	public Cinema findByName(String name){
		return this.cinemaRepository.findByName(name);
	}

	public Cinema create(Cinema cinema) throws Exception{
        if (cinema.getId() != null) {
            throw new Exception("ID must be null!");
        }
        Cinema newCinema = this.cinemaRepository.save(cinema);
        return newCinema;
	}
	
	public void delete(Long id){
		this.cinemaRepository.deleteById(id);
	}

	public void save(Cinema cinema){
        this.cinemaRepository.save(cinema);
    }
}