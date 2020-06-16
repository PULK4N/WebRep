package cinema.cinema.service;

import cinema.cinema.entity.*;
import cinema.cinema.repository.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private List<User> Users;
	@Autowired
	private ViewerRepository viewerRep;
	@Autowired
	private AdministratorRepository adminRep;
	@Autowired
	private ManagerRepository managerRep;
	
	private void addAllUsers() {
		Users = new ArrayList<User>();
		
		List<Viewer> viewers = viewerRep.findAll();
		List<Administrator> admins = adminRep.findAll();
		List<Manager> managers = managerRep.findAll();
		
		for(int i=0; i< viewers.size();i++) {
			Users.add(viewers.get(i));
		}
		
		for(int i=0; i< managers.size();i++) {
			Users.add(managers.get(i));
		}
		
		for(int i=0; i< admins.size();i++) {
			Users.add(admins.get(i));
		}
	}
	
	public List<User> findAll() {
		addAllUsers();
		return Users;
	}
	
	
}
