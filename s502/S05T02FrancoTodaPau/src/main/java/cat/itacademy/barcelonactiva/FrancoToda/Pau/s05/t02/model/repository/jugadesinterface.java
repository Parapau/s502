package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.Jugades;

public interface jugadesinterface extends MongoRepository <Jugades, String> {

}
