package com.ult.covoiturage.role;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleRepository repository;

    @GetMapping("/list")
    public String listRoles(Model model){
        List<Role> roles = (List<Role>) repository.findAll();
        long nbr = repository.count();
        if(roles.size()==0)
            roles =null;
        model.addAttribute("roles", roles);
        model.addAttribute("nbr", nbr);
        return "role/listRoles";
    }

    @GetMapping("/add")
    public String showAddRoleFrom(){
        return "role/addRole";
    }

    @PostMapping("/save")
    public String addRole(@RequestParam("role") String role){
        
        System.out.println(role);
        Role r = new Role(role);
        Role rSaved = repository.save(r);
        System.out.println("role = "+ rSaved);

        return "redirect:list";
    }


    @GetMapping("/delete/{id}")
    public String deleteVoiture(@PathVariable("id") Integer id){
        repository.deleteById(id);
        return "redirect:/role/list";
    }
    
}
