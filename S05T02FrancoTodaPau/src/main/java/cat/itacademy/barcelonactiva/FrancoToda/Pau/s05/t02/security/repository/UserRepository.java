package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.repository;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.model.User;

public interface UserRepository {

	public User findUserByNom(String nom);
}
