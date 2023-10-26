package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.context.annotation.Bean;

@Configuration
public class AplicationConfig {

	private final UserRepository userRepository;//TODO TODO TODO El nom ha de ser un altre segurament TODO TODO TODO
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return UserRepository.findByNom(username);
				
			}
			
		};
	}
}
