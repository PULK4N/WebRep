package cinema.cinema.controller;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import cinema.cinema.service.AdministratorService;
import cinema.cinema.service.ManagerService;
import cinema.cinema.service.UserService;
import cinema.cinema.service.ViewerService;
import cinema.cinema.entity.Administrator;
import cinema.cinema.entity.Manager;
import cinema.cinema.entity.Role;
import cinema.cinema.entity.User;
import cinema.cinema.entity.Viewer;
import cinema.cinema.entity.dto.UserDTO;
import cinema.cinema.entity.dto.ManagerDTO;

@RestController
@RequestMapping(value = "/api")
public class UserController{
	private final UserService userService;

	@Autowired
	private ViewerService viewerService;

	@Autowired
	private ManagerService managerService;


	@Autowired
	private AdministratorService administratorService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/users",
	consumes=MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)       
	public ResponseEntity<List<UserDTO>> getUsers(@RequestBody User RecivedUser) throws Exception{
		if(!(userService.returnUserByUsernameAndPassword(RecivedUser) instanceof Administrator)){
			System.out.println("Not an admin");
			return null;
		}

		List<User> users = userService.findAll();

		List<UserDTO> userDTOs = new ArrayList<>();

		for(User user : users){
			UserDTO userDTO;

			userDTO = new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getEmail(), user.getDateOfBirth(), user.getRole(),user.getActive());
		
			userDTOs.add(userDTO);
		}
		return new ResponseEntity<>(userDTOs, HttpStatus.OK);
	}

	@PostMapping(
		value="/register-user",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> register(@RequestBody User user) throws Exception{
		if(userService.returnUserByUsername(user.getUsername()) == null){
			User newUser = new User(user);
			if(user.getRole()==Role.Administrator){
				Administrator administrator = new Administrator(newUser);
				administrator.setActive(false);
				administratorService.create(administrator);
			}else if(user.getRole()==Role.Manager){
				Manager manager = new Manager(newUser);
				manager.setActive(false);
				managerService.create(manager);
			}else{
				Viewer viewer = new Viewer(newUser);
				viewer.setActive(true);
				viewerService.create(viewer);
			}
			return new ResponseEntity<>(newUser,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}

	@GetMapping(value = "/managers",
	produces = MediaType.APPLICATION_JSON_VALUE)       
	public ResponseEntity<List<ManagerDTO>> getManagers() throws Exception{

		return returnManagers();
		//return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}

	@PostMapping(
		value="/register-manager",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ManagerDTO>> registerManager(@RequestBody Manager _sentManager) throws Exception{
		if(userService.returnUserByUsername(_sentManager.getUsername()) == null){
			Manager manager = new Manager(_sentManager);
			manager.setActive(true);
			manager.setRole(Role.Manager);
			managerService.create(manager);
			
			return returnManagers();
		}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}

	@PostMapping(
		value="/delete-manager",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ManagerDTO>> deleteManager(@RequestBody Manager _sentManager) throws Exception{
		if(userService.returnUserByUsername(_sentManager.getUsername()) != null){
			User user = userService.returnUserByUsername(_sentManager.getUsername());
			if(user instanceof Manager){
				// Manager manager = (Manager)user;
				// if(manager.getCinema()!=null){
				// 	System.out.println(manager.getCinema().getId());
				// 	cinemaService.delete(manager.getCinema().getId());
				// }
				managerService.delete(user.getId());
			}
			return returnManagers();
		}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}


		@PostMapping(
		value="/get-user",
		consumes = MediaType.APPLICATION_JSON_VALUE,     // tip podataka koje metoda može da primi
        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody User user) throws Exception{
		if(userService.returnUserByUsernameAndPassword(user)!=null) {
			User requestedUser = new User(userService.returnUserByUsernameAndPassword(user));
			if((requestedUser).getActive() == false)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			if(userService.returnUserByUsernameAndPassword(user) instanceof Administrator){
				requestedUser.setRole(Role.Administrator);
			}else if(userService.returnUserByUsernameAndPassword(user) instanceof Manager){
				requestedUser.setRole(Role.Manager);
			}else{
				requestedUser.setRole(Role.Viewer);

			}
			return new ResponseEntity<>(requestedUser,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

	public ResponseEntity<List<ManagerDTO>> returnManagers(){
		List<Manager> managers = managerService.findAll();

		List<ManagerDTO> managerDTOs = new ArrayList<>();
		for(Manager _manager : managers){
			ManagerDTO managerDTO;
			
			managerDTO = new ManagerDTO(_manager.getId(), _manager.getUsername(), _manager.getPassword(), _manager.getFirstName(), _manager.getLastName(), _manager.getPhoneNumber(), _manager.getEmail(), _manager.getDateOfBirth(), _manager.getRole(),_manager.getActive());	
			managerDTOs.add(managerDTO);
		}
		return new ResponseEntity<>(managerDTOs, HttpStatus.OK);
	}
}

		/*if(!(userService.returnUserByUsernameAndPassword(RecivedUser) instanceof Administrator)){
			System.out.println("Not an admin");
			return null;
		}

		List<User> users = userService.findAll();

		List<UserDTO> userDTOs = new ArrayList<>();

		for(User user : users){
			UserDTO userDTO;

			userDTO = new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getEmail(), user.getDateOfBirth(), user.getRole());
			if(user.getRole()==Role.Manager)
				userDTOs.add(userDTO);
		}
		return new ResponseEntity<>(userDTOs, HttpStatus.OK);*/

		/*
	// @PostMapping(
	// 	value="/change-user",
	// 	consumes=MediaType.APPLICATION_JSON_VALUE,
	// 	produces=MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<User> change(@RequestBody User user) throws Exception{
	// 	User requestedUser = userService.returnUserByUsername(user.getUsername());
	// 	requestedUser.
	// 	return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	// }*/