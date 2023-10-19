package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.dto.UnificatDTO;
import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.service.JocService;

@RestController
@RequestMapping("/players")
public class JocController {

	@Autowired
	JocService service;
	
	
	@PostMapping("/")
	public ResponseEntity<UnificatDTO> add (@RequestParam String nom){
		
		UnificatDTO unificat = new UnificatDTO(nom);
		ResponseEntity<UnificatDTO> retorn = new ResponseEntity<UnificatDTO>(unificat, HttpStatus.OK);
				
		service.save(unificat);
		
		return retorn;
	}
	
	
	@PutMapping("/")//no pot arribar cap request sense o nom o id IMPORTANT
	public ResponseEntity<UnificatDTO> canviaNom (@RequestParam (value = "id", required = false, defaultValue = "-1")long id , @RequestParam (value = "nomVell", required = false)String nomVell, @RequestParam String nomNou ) {
		ResponseEntity<UnificatDTO> retorn;
		UnificatDTO unificat;
		if (!(id == -1) && service.findById(id).isPresent()) {
			unificat = service.findById(id).get();
			unificat.setNom(nomNou);
			service.update(unificat);
			retorn = new ResponseEntity<UnificatDTO>(unificat, HttpStatus.OK);
		} else if (!nomVell.equals(null) && service.findByName(nomVell).isPresent()){
			unificat = service.findByName(nomVell).get();
			unificat.setNom(nomNou);
			service.update(unificat);
			retorn = new ResponseEntity<UnificatDTO>(unificat, HttpStatus.OK);
		} else if (nomVell.equals(null) && id == 1) {//Aixo no hauria de passar mai
			for (int i = 0; i<24; i++) {
				System.err.println("pau l'has cagat");
				System.err.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			}
			retorn = new ResponseEntity<UnificatDTO>(HttpStatus.BAD_REQUEST);
			
		} else {
			retorn = new ResponseEntity<UnificatDTO>(HttpStatus.NOT_FOUND);
		}
		
		return retorn;
	}

	
	
	@PostMapping("/{id}/games/")
	public ResponseEntity<UnificatDTO>/*no estic segur de que hagi de retornar unificat*/ jugar (@PathVariable("id") long id){
		//TODO
		//TODO
		return null;
	}
	
	
	
	@DeleteMapping("/{id}/games/")
	public ResponseEntity<UnificatDTO> elminarPartides (@PathVariable("id") long id){
		ResponseEntity<UnificatDTO> retorn;
		
		if (service.deleteJugadesById(id)) {
			retorn = new ResponseEntity<UnificatDTO>(service.findById(id).get(), HttpStatus.OK);
		} else {
			retorn = new ResponseEntity<UnificatDTO>(HttpStatus.NOT_FOUND);
		}
		
		return retorn;
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<UnificatDTO>> getAll () {
		ResponseEntity<List<UnificatDTO>> retorn = new ResponseEntity<List<UnificatDTO>>(service.findAll(), HttpStatus.OK);		
		
		return retorn;
	}
	
	
	
	@GetMapping("/{id}/games/")//he fet aixo mig enfadat ossigui que potser esta fatal pau puto gilipolles de merda
	public ResponseEntity<UnificatDTO> getOne (@PathVariable("id") long id) {
		ResponseEntity<UnificatDTO> retorn;
		Optional<UnificatDTO> unificat = service.findById(id);
		
		if (unificat.isPresent()) {
			retorn = new ResponseEntity<UnificatDTO> (unificat.get(), HttpStatus.OK);
		} else {
			retorn = new ResponseEntity<UnificatDTO> (HttpStatus.NOT_FOUND);
		}
		
		return retorn;
	}
	
	
	@GetMapping("/ranking/")//Aixi es segurament com hauria de mnostrar els jugadors
	public ResponseEntity<List<String>> getPercents () {
		List<UnificatDTO> unificats = service.findAll();
		List<String> llista = new ArrayList<>();
		ResponseEntity<List<String>> retorn;
		
		for (UnificatDTO dto : unificats) {
			llista.add(dto.nomesPercent());
		}
		
		
		return null;
	}
	
	
}




















