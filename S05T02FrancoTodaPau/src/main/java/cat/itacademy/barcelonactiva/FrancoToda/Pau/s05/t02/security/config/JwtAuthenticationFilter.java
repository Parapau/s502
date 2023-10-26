package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.config;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.config.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	
	 
	public JwtAuthenticationFilter () {
		this.jwtService = new JwtService();
		//TODO
		//TODO
		//TODO constructor necesari si poses un final p algo, encara no ho entenc 100% pero ho deixo anotat
		//TODO
		//TODO
		this.userDetailsService = null;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final String authHeader = request.getHeader("Authoritzation");
		
		final String jwt;
		final String nom;
		if (authHeader == null || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
		} else {
			jwt = authHeader.substring(7);
			
			nom = jwtService.extractNom(jwt);
			
			if (nom != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(nom);
			}
			
			
			
			
		}
	}

}
