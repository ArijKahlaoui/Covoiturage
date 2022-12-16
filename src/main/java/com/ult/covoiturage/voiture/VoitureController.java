package com.ult.covoiturage.voiture;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ult.covoiturage.pilote.Pilote;
import com.ult.covoiturage.pilote.PiloteRepository;

@Controller
@RequestMapping("/voiture")
public class VoitureController {

    public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";

    @Autowired
    private VoitureRepository voitureRepository;
    @Autowired
    private PiloteRepository piloteRepo;



     @GetMapping("/list")
    public String listeVoitures(Model model){
        List<Voiture> listVoiture = (List<Voiture>) voitureRepository.findAll();
        List<Pilote> listPilote = (List<Pilote>) piloteRepo.findAll();
        model.addAttribute("listVoiture", listVoiture);
        model.addAttribute("listePilote", listPilote);
        return "voiture/index";
    }

    @GetMapping("/new")
    public String showFormFournisseur(Model model) {
        model.addAttribute("voiture", new Voiture());
        return "voiture/AjouterVoiture";
    }

    @PostMapping("/save")
    public String saveVoiture(Voiture voiture,@RequestParam("files") MultipartFile[] files){
        /// part upload
        StringBuilder fileName = new StringBuilder();
        MultipartFile file = files[0];
        Path fileNameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
        
        fileName.append(file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
        e.printStackTrace();
        }
        voiture.setPhoto(fileName.toString());

        voitureRepository.save(voiture);
        return  "redirect:list";
    }

    @GetMapping("/edit/{id}")
    public String editVoiture(@PathVariable("id") Integer id,Model model){
        Voiture voiture = voitureRepository.findById(id).get();
        model.addAttribute("voiture", voiture);
        return "voiture/voiture_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteVoiture(@PathVariable("id") Integer id){
        voitureRepository.deleteById(id);
        return "redirect:/voiture/list";
    }
}
