package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@Configuration
public class AplicationConfig {

	@Autowired
	UserRepository userRepository;
	
	
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Optional<UserDetails> detalls = userRepository.findUserByNom(username);
				
				UserDetails retorn;
				
				if (detalls.isPresent()) {
					retorn = detalls.get();
				} else {
					throw new UsernameNotFoundException("No hi ha cap usuari amb aquest nom");
				}
				 
				return retorn;
			}
			
		};
	}
}
