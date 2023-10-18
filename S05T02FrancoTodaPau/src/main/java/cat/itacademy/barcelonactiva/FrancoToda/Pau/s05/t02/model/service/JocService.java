package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain.Jugades;
import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain.Jugador;
import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.dto.UnificatDTO;
import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.repository.JugadorRepository;
import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.repository.jugadesRepository;

@Service
public class JocService {

	
	@Autowired
	jugadesRepository jugadesRepo;
	

	@Autowired
	JugadorRepository jugadorRepo;
	
	
	public List<UnificatDTO> findAll() {
		
		List<Jugador> jugadors = jugadorRepo.findAll();
		List<UnificatDTO> retorn = new ArrayList<UnificatDTO>();
		
		for (Jugador jugador : jugadors) {
			retorn.add(UnificatDTO.unificar(jugador, jugadesRepo.findById(jugador.getID())));
		}
		
		return retorn;
	}
	
	
	public Optional<UnificatDTO> findById (long id){
		Optional<UnificatDTO> retorn = Optional.empty();
		Optional<Jugador> jugador = jugadorRepo.findById(id);
		Optional<Jugades> jugades = jugadesRepo.findById(id);
		
		if (jugador.isPresent() ) {
			retorn = Optional.ofNullable(UnificatDTO.unificar(jugador.get(), jugades));
		} else {
			System.err.println("jugador no trobat");
		}
		
		return retorn;
	}
	
	
	
	
	
	public UnificatDTO save (UnificatDTO unificat) {
		
		Object[] separat = unificat.separar();
		
		Jugador jugador = (Jugador) separat[0];
		
		Jugades jugades = (Jugades) separat[1];
		
		jugadorRepo.save(jugador);
		jugadesRepo.save(jugades);
		
		return unificat;
	}
	
	
	public UnificatDTO update (UnificatDTO unificat) {
		
		Object[] separat = unificat.separar();
		
		Jugador jugador = (Jugador) separat[0];
		
		Jugades jugades = (Jugades) separat[1];
		
		jugadorRepo.save(jugador);
		jugadesRepo.save(jugades);
		
		return UnificatDTO.unificar(jugador, jugades);
	}
	
	
	public void deleteById (long id) {
		jugadorRepo.deleteById(id);
		jugadesRepo.deleteById(id);
	}
	
	
	public void deleteJugadesById(long id) {
		jugadesRepo.deleteById(id);
	}
	
	
	public void deleteAll() {
		jugadorRepo.deleteAll();
		jugadesRepo.deleteAll();
	}
	
	
}





















