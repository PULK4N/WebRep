package cinema.cinema.controller;

import cinema.cinema.service.*;
import java.util.*;
import cinema.cinema.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class UserController{
	private UserService userService;

	@GetMapping(value="/")
	public String welcome() {
		return "index.html";
	}
	@GetMapping("/Users")
	public String getUsers(Model model){
		List<User> userList = this.userService.findAll();
		model.addAttribute("users", userList);
		return "Users.html";
	}
}