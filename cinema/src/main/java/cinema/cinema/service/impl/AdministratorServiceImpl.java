package cinema.cinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cinema.cinema.entity.Administrator;
import cinema.cinema.repository.AdministratorRepository;
import cinema.cinema.service.AdministratorService;
import java.util.*;

@Service
public class AdministratorServiceImpl implements AdministratorService{
	@Autowired
    AdministratorRepository administratorRepository;
    


	@Override
	public Administrator create(Administrator administrator) throws Exception{
        if (administrator.getId() != null) {
            throw new Exception("ID must be null!");
        }
        Administrator newAdministrator = this.administratorRepository.save(administrator);
        return newAdministrator;
    }

    public Administrator findOne(Long id){
		return this.administratorRepository.getOne(id);
	}

    public Administrator update(Administrator administrator) throws Exception{
        Administrator administratorToUpdate = this.administratorRepository.getOne(administrator.getId());
        if (administratorToUpdate == null) {
            throw new Exception("administrator doesnt exist!");
        }

        administratorToUpdate.setFirstName(administrator.getFirstName());

        Administrator savedAdministrator = this.administratorRepository.save(administratorToUpdate);
        return savedAdministrator;
    }

    public void delete(Long id){
		this.administratorRepository.deleteById(id);
	}

    public List<Administrator> findAll(){
		return this.administratorRepository.findAll();
    }
    
    public void save(Administrator administrator){
        this.administratorRepository.save(administrator);
    }
}