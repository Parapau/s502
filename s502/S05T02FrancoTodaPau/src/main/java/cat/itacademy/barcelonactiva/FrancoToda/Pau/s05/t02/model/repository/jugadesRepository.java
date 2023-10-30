package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain.Jugades;

public interface jugadesRepository extends MongoRepository <Jugades, Long> {
	
}
