package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
	
	
	public Optional<Jugador> findByName(String nom);

}
