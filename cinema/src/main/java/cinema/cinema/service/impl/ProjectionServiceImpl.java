package cinema.cinema.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cinema.cinema.entity.Projection;
import cinema.cinema.repository.ProjectionRepository;
import cinema.cinema.service.ProjectionService;

@Service
public class ProjectionServiceImpl implements ProjectionService{

	@Autowired
	ProjectionRepository projectionRepository;

	@Override
	public Projection create(Projection projection) throws Exception{
        if (projection.getId() != null) {
            throw new Exception("ID must be null!");
        }
        Projection newProjection = this.projectionRepository.save(projection);
        return newProjection;
	}

	public List<Projection> findAll(){
		return this.projectionRepository.findAll();
	}

	public Projection findOne(Long id){
		return this.projectionRepository.getOne(id);
	}

	public void delete(Long id){
		this.projectionRepository.deleteById(id);
	}

	public void save(Projection projection){
        this.projectionRepository.save(projection);
    }
}