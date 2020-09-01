package cinema.cinema.service.impl;

import cinema.cinema.entity.*;
import cinema.cinema.repository.*;
import java.util.*;
import cinema.cinema.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	private List<User> Users;
	@Autowired
	private ViewerRepository viewerRep;
	@Autowired
	private AdministratorRepository adminRep;
	@Autowired
	private ManagerRepository managerRep;

	public User returnUserByUsername(String username){
		addAllUsers();
		for (User _user : Users) {
			if( _user.getUsername().equals(username)){
				return _user;
			}
		}
		return null;
	}

	public User returnUserByUsernameAndPassword(User user){
		addAllUsers();
		for (User _user : Users) {
		  if( _user.getUsername().equals(user.getUsername())  && _user.getPassword().equals(user.getPassword()))
				return _user;
		}
		return null;
	}
	
	public void addAllUsers() {
		Users = new ArrayList<User>();
		
		List<Viewer> viewers = viewerRep.findAll();
		List<Administrator> admins = adminRep.findAll();
		List<Manager> managers = managerRep.findAll();
		
		for(Viewer viewer : viewers) {
			Users.add((User)viewer);
		}
		for(Manager manager : managers) {
			Users.add((User)manager);
		}
		for(Administrator administrator : admins) {
			Users.add((User)administrator);
		}
	}
	
	public List<User> findAll() {
		addAllUsers();
		return Users;
	}

	public List<Viewer> findAllViewers(){
		return viewerRep.findAll();
	}
	public Viewer findOneViewer(Long id){
		return viewerRep.getOne(id);
	}

	public List<Administrator> findAllAdministrators(){
		return adminRep.findAll();
	}
	public Administrator findOneAdministrator(Long id){
		return adminRep.getOne(id);
	}

	public List<Manager> findAllManagers(){
		return managerRep.findAll();
	}
	public Manager findOneManager(Long id){
		return managerRep.getOne(id);
	}



}