package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.model.User;



public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<UserDetails> findUserByNom(String nom);
}
