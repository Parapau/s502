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

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain.Daus;
import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.dto.UnificatDTO;
import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.service.JocService;

@RestController
@RequestMapping("/players")
public class JocController {

	@Autowired
	JocService service;
	
	
	@PostMapping("/")
	public ResponseEntity<UnificatDTO> add (@RequestParam (value = "nom", required = false, defaultValue = "Anonim") String nom){
		ResponseEntity<UnificatDTO> retorn;
		UnificatDTO unificat;
		
		if (service.isName(nom)) {
			retorn = new ResponseEntity<UnificatDTO> (HttpStatus.BAD_REQUEST);
			
		} else {
			unificat = new UnificatDTO(nom);
			retorn = new ResponseEntity<UnificatDTO>(unificat, HttpStatus.CREATED);
			service.save(unificat);
		}
		
		return retorn;
	}
	
	
	@PutMapping("/")
	public ResponseEntity<UnificatDTO> canviaNom (@RequestParam (value = "id", required = false, defaultValue = "-1")long id , @RequestParam (value = "nomVell", required = false, defaultValue = "Anonim")String nomVell, @RequestParam String nomNou ) {
		ResponseEntity<UnificatDTO> retorn;
		UnificatDTO unificat = null;
		boolean esta = false;
		if (!(id == -1) && service.findById(id).isPresent()) {
			unificat = service.findById(id).get();
			esta = true;
		} else if (!nomVell.equals("Anonim") && service.findByName(nomVell).isPresent()){
			unificat = service.findByName(nomVell).get();
			esta = true;
		} 
			
		if (esta && !service.isName(nomNou)) {
			unificat.setNom(nomNou);
			service.update(unificat);
			retorn = new ResponseEntity<UnificatDTO>(unificat, HttpStatus.OK);
		} else if (esta){
			retorn = new ResponseEntity<UnificatDTO>(HttpStatus.NOT_ACCEPTABLE);
		} else if (id == -1 && nomVell.equals("Anonim")){
			retorn = new ResponseEntity<UnificatDTO>(HttpStatus.BAD_REQUEST);
		} else {
			retorn = new ResponseEntity<UnificatDTO>(HttpStatus.NOT_FOUND);
		}
		
		
		return retorn;
	}

	
	
	@PostMapping("/{id}/games/")
	public ResponseEntity<String> jugar (@PathVariable("id") long id){
		ResponseEntity<String> retorn;
		Optional<UnificatDTO> buscar = service.findById(id);
		Daus daus;
		
		if (buscar.isPresent()) {
			daus = buscar.get().jugar();
			retorn = new ResponseEntity<String>(daus.toString(), HttpStatus.CREATED);
			service.save(buscar.get());
		} else {
			retorn = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
		return retorn;
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
	
	
//	@GetMapping("/")
//	public ResponseEntity<List<UnificatDTO>> getAll () {
//		ResponseEntity<List<UnificatDTO>> retorn = new ResponseEntity<List<UnificatDTO>>(service.findAll(), HttpStatus.OK);		
//		
//		return retorn;
//	}
	
	@GetMapping("/")
	public ResponseEntity<String> getAllPretty () {
		List<UnificatDTO> llista = new ResponseEntity<List<UnificatDTO>>(service.findAll(), HttpStatus.OK).getBody();		
		ResponseEntity<String> retorn;
		String tot = "";
		if (!(llista.size()==0)) {
			for (UnificatDTO dto : llista) {
				tot += dto.toString() + "\n\n";
			}
			retorn = ResponseEntity.ok(tot);
		} else {
			retorn = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		
		return retorn;
	}
	
	
	@GetMapping("/{id}/games/")
	public ResponseEntity<String> getOne (@PathVariable("id") long id) {
		ResponseEntity<String> retorn;
		Optional<UnificatDTO> unificat = service.findById(id);
		
		if (unificat.isPresent()) {
			retorn = new ResponseEntity<String> (unificat.get().toString(), HttpStatus.OK);
		} else {
			retorn = new ResponseEntity<String> (HttpStatus.NOT_FOUND);
		}
		
		return retorn;
	}
	
	
//	@GetMapping("/{id}/games/pretty")
//	public ResponseEntity<String> getOnePretty (@PathVariable("id") long id) {
//		ResponseEntity<String> retorn;
//		Optional<UnificatDTO> unificat = service.findById(id);
//		
//		if (unificat.isPresent()) {
//			retorn = new ResponseEntity<String> (unificat.get().toString(), HttpStatus.OK);
//		} else {
//			retorn = new ResponseEntity<String> (HttpStatus.NOT_FOUND);
//		}
//		
//		return retorn;
//	}
	
	
	@GetMapping("/ranking/")//Aixi es segurament com hauria de mostrar els jugadors
	public ResponseEntity<List<String>> getPercents () {
		List<UnificatDTO> unificats = service.findAll();
		List<String> llista = new ArrayList<>();
		ResponseEntity<List<String>> retorn;
		int percentatge = 0;
		
		for (UnificatDTO dto : unificats) {
			llista.add(dto.nomesPercent());
			percentatge += dto.percentatge();
		}
		percentatge = percentatge/llista.size();
		if (!(llista.size()==0) && !(percentatge == 100)) {
			llista.add("El percentatge mitja de victories es de " + percentatge + "%");
		} else if (!(llista.size()==0)){
			llista.add("WOW, tothom ha guanyat tot, felicitats 👍");
		}
		
		retorn = new ResponseEntity<List<String>>(llista, HttpStatus.OK);
		
		return retorn;
	}
	
	
	
	
	@GetMapping("/games/")
	public ResponseEntity<String> getNoms() {
//		List<UnificatDTO> unificats = service.findAll();
//		List<String> llista = new ArrayList<>();
//		ResponseEntity<List<String>> retorn;
//		
//		for (UnificatDTO dto : unificats) {
//			llista.add(dto.toString());
//		}
//		
//		retorn = new ResponseEntity<List<String>>(llista, HttpStatus.OK);
//		
//		return retorn;
		List<UnificatDTO> llista = new ResponseEntity<List<UnificatDTO>>(service.findAll(), HttpStatus.OK).getBody();		
		ResponseEntity<String> retorn;
		String tot = "";
		if (!(llista.size()==0)) {
			for (UnificatDTO dto : llista) {
				tot += dto.toString() + "\n\n";
			}
			retorn = ResponseEntity.ok(tot);
		} else {
			retorn = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		
		return retorn;
	}
	
	
	
	@GetMapping("/ranking/winner/")
	public ResponseEntity<UnificatDTO> getWinner() {
		ResponseEntity<UnificatDTO> retorn;
		List<UnificatDTO> unificats= service.findAll();

		unificats.sort(null);
		
		retorn = new ResponseEntity<UnificatDTO> (unificats.get(unificats.size() - 1), HttpStatus.OK);
		
		return retorn;
	}
	
	
	@GetMapping("/ranking/loser/")
	public ResponseEntity<UnificatDTO> getPringat() {
		ResponseEntity<UnificatDTO> retorn;
		List<UnificatDTO> unificats= service.findAll();

		System.err.println("A");
		unificats.sort(null);
		
		retorn = new ResponseEntity<UnificatDTO> (unificats.get(0), HttpStatus.OK);
		
		return retorn;
	}
	
	
	@GetMapping("/aTomarPorCulo/")
	public ResponseEntity<Object> deleteAll() {
		service.deleteAll();
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
}




















