package cinema.cinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cinema.cinema.entity.Viewer;
import cinema.cinema.repository.ViewerRepository;
import cinema.cinema.service.ViewerService;
import java.util.*;

@Service
public class ViewerServiceImpl implements ViewerService{
	@Autowired
	ViewerRepository viewerRepository;

	@Override
	public Viewer create(Viewer viewer) throws Exception{
        if (viewer.getId() != null) {
            throw new Exception("ID must be null!");
        }
        Viewer newViewer = this.viewerRepository.save(viewer);
        return newViewer;
    }

    public Viewer findByUsernameAndPassword(String username, String password){
        return viewerRepository.findByUsernameAndPassword(username, password);
    }

    public Viewer findOne(Long id){
		return this.viewerRepository.getOne(id);
	}

    public Viewer update(Viewer viewer) throws Exception{
        Viewer viewerToUpdate = this.viewerRepository.getOne(viewer.getId());
        if (viewerToUpdate == null) {
            throw new Exception("viewer doesnt exist!");
        }

        viewerToUpdate.setFirstName(viewer.getFirstName());

        Viewer savedViewer = this.viewerRepository.save(viewerToUpdate);
        return savedViewer;
    }

    public void delete(Long id){
		this.viewerRepository.deleteById(id);
	}

    public List<Viewer> findAll(){
		return this.viewerRepository.findAll();
    }
    
    public void save(Viewer viewer){
        this.viewerRepository.save(viewer);
    }
}