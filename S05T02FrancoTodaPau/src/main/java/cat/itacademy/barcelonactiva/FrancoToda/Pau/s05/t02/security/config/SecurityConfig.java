package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig { 

	private JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter();
	
	@Autowired
	AplicationConfig config;

	
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity https) throws Exception{
		
		https.csrf((csrf) -> csrf.disable())
		.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers("/autenticacio/**").permitAll().anyRequest().authenticated())
		.sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(config.authenticationProvider())
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		return https.build();
	}
	
	
	
	
}


