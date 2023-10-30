package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig { //aquesta merda esta deprecada

	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity https) throws Exception{
		
		https.csrf((csrf) -> csrf.disable())
		.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers("").permitAll())
		.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.anyRequest().authenticated())
		.sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(null, null);
		
		
		
		
		return null;
	}
	
	
	
	AuthenticationProvider authenticationProvider = null;
}


