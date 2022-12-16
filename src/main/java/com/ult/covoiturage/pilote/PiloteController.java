package com.ult.covoiturage.pilote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pilote")      
class PiloteController {

    @Autowired
    PiloteRepository piloteRepository;

    
    @GetMapping("/list")
    public String listPilote(Model model) {
        List<Pilote> listPilotes = (List<Pilote>) piloteRepository.findAll();
        model.addAttribute("listPilotes", listPilotes);
        return "pilote/index";
    }

    

   
}
