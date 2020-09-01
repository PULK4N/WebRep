package cinema.cinema.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cinema.cinema.entity.Auditorium;
import cinema.cinema.repository.AuditoriumRepository;
import cinema.cinema.service.AuditoriumService;

@Service
public class AuditoriumServiceImpl implements AuditoriumService{

	@Override
	public Auditorium create(Auditorium auditorium) throws Exception{
        if (auditorium.getId() != null) {
            throw new Exception("ID must be null!");
        }
        Auditorium newAuditorium = this.auditoriumRepository.save(auditorium);
        return newAuditorium;
    }

	@Autowired
	AuditoriumRepository auditoriumRepository;

	public List<Auditorium> findAll(){
		return this.auditoriumRepository.findAll();
	}

	public Auditorium findOne(Long id){
		return this.auditoriumRepository.getOne(id);
	}

	public void delete(Long id){
		this.auditoriumRepository.deleteById(id);
	}

	public void save(Auditorium auditorium){
        this.auditoriumRepository.save(auditorium);
    }
}