package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.lang.NonNull;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	public JwtAuthenticationFilter () {}
	
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authoritzation");
		System.err.println(":|");
		final String jwt;
		final String nom;
		if (authHeader == null || !authHeader.startsWith("Bearer")) {
			System.err.println(":)");
			filterChain.doFilter(request, response);
			System.err.println("Aixo no hauria de sortir");
		} else {
			jwt = authHeader.substring(7);
			
			nom = jwtService.extractNom(jwt);
			
			System.err.println(":(");
			
			if (nom != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(nom);
				if (jwtService.isTokenValid(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken autenticacio = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					
					autenticacio.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(autenticacio);
				}
			}
			
			filterChain.doFilter(request, response);
			
		}
	}
}
