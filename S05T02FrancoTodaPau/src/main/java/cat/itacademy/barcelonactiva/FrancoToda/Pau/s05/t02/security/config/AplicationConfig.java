package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@Configuration
public class AplicationConfig {

	@Autowired
	private UserRepository userRepository;//TODO TODO TODO El nom ha de ser un altre segurament TODO TODO TODO
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return userRepository.findUserByNom(username);
				
			}
			
		};
	}
}
