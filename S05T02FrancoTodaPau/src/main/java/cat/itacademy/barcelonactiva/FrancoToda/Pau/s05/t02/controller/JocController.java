package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.dto.UnificatDTO;

@RestController
@RequestMapping("/players")
public class JocController {

	
	@PostMapping("/")
	public ResponseEntity<UnificatDTO> add (@RequestParam String nom){
		
		
		
		return null;
	}
}
