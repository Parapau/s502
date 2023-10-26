package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.repository;

import org.springframework.security.core.userdetails.UserDetails;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.model.User;

public interface UserRepository {

	public UserDetails findUserByNom(String nom);
}
