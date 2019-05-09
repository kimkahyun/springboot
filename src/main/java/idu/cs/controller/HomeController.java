package idu.cs.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import idu.cs.domain.User;
import idu.cs.exception.ResourceNotFoundException;
import idu.cs.repository.UserRepository;

@Controller
public class HomeController {
	@Autowired UserRepository userRepo;
	
	@GetMapping("/test")
	public String home(Model model) {
		model.addAttribute("test","인덕컴소");
		model.addAttribute("kkh","김가현");
		return "index";
	}
	@GetMapping("/users")
	public String getAllUser(Model model) {
		model.addAttribute("user",userRepo.findAll());
		return "userlist";
	}
	
	@GetMapping("/register")
	public String loadReg(Model model) {
		return"regform";
	}
	@GetMapping("/user/{id}")
	public String getUserById(@PathVariable(value = "id") Long userId, Model model)
		throws ResourceNotFoundException {
		User user = userRepo.findById(userId).get();
		model.addAttribute("id",""+userId);
		model.addAttribute("name",user.getName());
		model.addAttribute("company",user.getCompany());
		return "user";
	}
	@GetMapping("/welcome")
	public String welcome(Model model) {
		return"welcome";
	}
	@PostMapping("/create")
	public String createUser(@Valid @RequestBody User user, Model model) {
		userRepo.save(user);
		model.addAttribute("user",userRepo.findAll());
		return "redirect:/users";
	}
}
