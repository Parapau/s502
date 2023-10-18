package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
	
	
	public Jugador findByName(String nom);

}
