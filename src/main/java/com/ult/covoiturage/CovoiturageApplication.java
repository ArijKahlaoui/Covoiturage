package com.ult.covoiturage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ult.covoiturage.user.LoginController;
import com.ult.covoiturage.voiture.VoitureController;
import java.io.File;

@SpringBootApplication
public class CovoiturageApplication {

	
	public static void main(String[] args) {
		new File(VoitureController.uploadDirectory).mkdir();
		new File(LoginController.uploadDirectory).mkdir();
		SpringApplication.run(CovoiturageApplication.class, args);
	}

}
