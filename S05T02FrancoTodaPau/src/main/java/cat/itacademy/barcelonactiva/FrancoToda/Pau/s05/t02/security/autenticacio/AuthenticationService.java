package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.autenticacio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.config.JwtService;
import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.model.Role;
import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.repository.UserRepository;
import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.model.User;

@Service
public class AuthenticationService {

	@Autowired
	UserRepository userRepo;// = new UserService();

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationManager authManager;
	
	public AuthenticationResponse authenticate (Request request) {	
		
		UsernamePasswordAuthenticationToken nomToken = new UsernamePasswordAuthenticationToken(request.getNom(), request.getContrasenya());
		
		authManager.authenticate(nomToken);
		
		var user = userRepo.findUserByNom(request.getNom()).orElseThrow();

		var token = jwtService.generateToken(user);
		
		return new AuthenticationResponse(token);
	}
	
	
	public AuthenticationResponse register (Request request) {
		
		User user = new User(request.getNom(), encoder.encode(request.getContrasenya()), Role.USUARI);
		
		userRepo.save(user);
		
		var token = jwtService.generateToken(user);
		
		return new AuthenticationResponse(token);
	}
	
	
	
	
}
