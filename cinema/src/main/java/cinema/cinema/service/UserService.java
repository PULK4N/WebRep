package cinema.cinema.service;

import cinema.cinema.entity.*;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	
	List<User> findAll();

	User returnUserByUsernameAndPassword(User user);

	User returnUserByUsername(String username);
}
