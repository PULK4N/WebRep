package cinema.cinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cinema.cinema.entity.Manager;
import cinema.cinema.repository.ManagerRepository;
import cinema.cinema.service.ManagerService;
import java.util.*;

@Service
public class ManagerServiceImpl implements ManagerService{
	@Autowired
    ManagerRepository managerRepository;
    
    // public Manager findByUsernamePassword(String username, String password){
        
    // }

	@Override
	public Manager create(Manager manager) throws Exception{
        if (manager.getId() != null) {
            throw new Exception("ID must be null!");
        }
        Manager newManager = this.managerRepository.save(manager);
        return newManager;
    }

    public Manager findOne(Long id){
		return this.managerRepository.getOne(id);
	}

    public Manager update(Manager manager) throws Exception{
        Manager managerToUpdate = this.managerRepository.getOne(manager.getId());
        if (managerToUpdate == null) {
            throw new Exception("manager doesnt exist!");
        }

        managerToUpdate.setFirstName(manager.getFirstName());

        Manager savedManager = this.managerRepository.save(managerToUpdate);
        return savedManager;
    }

    public void delete(Long id){
		this.managerRepository.deleteById(id);
	}

    public List<Manager> findAll(){
		return this.managerRepository.findAll();
    }
    
    public void save(Manager manager){
        this.managerRepository.save(manager);
    }
}