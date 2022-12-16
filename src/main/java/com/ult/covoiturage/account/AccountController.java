package com.ult.covoiturage.account;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ult.covoiturage.role.Role;
import com.ult.covoiturage.role.RoleRepository;
import com.ult.covoiturage.user.User;
import com.ult.covoiturage.user.UserRepository;


@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final UserRepository userRepository;
	
	
	private final RoleRepository roleRepository;

    @Autowired
	public AccountController(UserRepository userRepository,RoleRepository roleRepository) {
	 this.userRepository = userRepository;
	 this.roleRepository = roleRepository;
	}



    @GetMapping("/list")
	public String listUsers(Model model) {
	
		List<User> users = (List<User>) userRepository.findAll();
		long nbr = userRepository.count();
		if(users.size()==0)
			users = null;
		model.addAttribute("users", users);
		model.addAttribute("nbr", nbr);
		return "accounts/listUsers";
	}

	@GetMapping("enable/{id}")
	public String enableUserAcount(@PathVariable ("id") int id) {
	
		User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id:" + id));
		user.setActive(1);
		userRepository.save(user);
		return "redirect:../list";
	}

	@GetMapping("disable/{id}")
	public String disableUserAcount(@PathVariable ("id") int id) {
	
		User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id:" + id));
		user.setActive(0);
		userRepository.save(user);
		return "redirect:../list";
	}

	@PostMapping("updateRole")
	public String UpdateUserRole(@RequestParam ("id") int id,@RequestParam ("newrole")String newRole)
	{
		User user = userRepository.findById(id).orElseThrow(()->
		new IllegalArgumentException("Invalid User Id:" + id));
		 
		Role userRole = roleRepository.findByRole(newRole);
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		 
		userRepository.save(user);
		return "redirect:list";
 	} 
}
