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
	UserDetailsService userDetailsService;// = new CustomUserDetailsService();
	
	@Autowired
	JwtService jwtService;// = new JwtService();//TODO TODO TODO No entenc perque l'he de crear jo			puto lombok de merda
	
	public JwtAuthenticationFilter () {}
	
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
		
		jwtService.funciona();
		
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String nom;
		System.out.println(authHeader);
		if ((authHeader == null) || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
		} else {
			jwt = authHeader.substring(7);
			
			nom = jwtService.extractNom(jwt);
			
			
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
