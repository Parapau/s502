package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@Configuration
public class AplicationConfig {

	@Autowired
	UserRepository userRepository;

//	@Autowired
//	UserDetailsService userDetailsService;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				System.err.println("crec que el problema esta aqui");
				Optional<UserDetails> detalls = userRepository.findUserByNom(username);
				UserDetails retorn;
				System.err.println(username);
				
				if (detalls.isPresent()) {
					System.err.println(detalls.get().getUsername());
					retorn = detalls.get();
				} else {
					throw new UsernameNotFoundException("No hi ha cap usuari amb aquest nom");
				}
				 
				return retorn;
			}
			
			
			
		};
	}
	
	
	@Bean
	public AuthenticationProvider authenticationProvider () {
		System.err.println("hola, que tal, soc el proveïdor");
		DaoAuthenticationProvider autenticador = new DaoAuthenticationProvider();
		autenticador.setUserDetailsService(userDetailsService());
		autenticador.setPasswordEncoder(passwordEncoder());
		
		return autenticador;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.err.println("les patates estan molt bones, aixo es un missatge xifrat (el missatge de veritat es que les patates estan MOLT bones)");
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		System.err.println("sui");
		return config.getAuthenticationManager();
	}
	
	
}
