package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.autenticacio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacio")
public class SecurityController {

	@Autowired
	AuthenticationService service;
	
	
	@GetMapping("/patata")
	public ResponseEntity<String> proba (){
		return ResponseEntity.ok("les patates estan molt bones i tambe permeses");
	}
	
	
	@GetMapping("/registrar")
	public ResponseEntity<String> joder () {
		return ResponseEntity.ok("Si aixo funciona m'enfado");
	}
	
	
	@PostMapping("/registrar")
	public ResponseEntity<AuthenticationResponse> Register(@RequestBody RegisterRequest request) {
		
		System.out.println("BLA");
		
		return ResponseEntity.ok(service.register(request));
	}
	
	
	
	
	@PutMapping("/autenticar")
	public ResponseEntity<AuthenticationResponse> Authenticate (@RequestBody AuthenticationRequest request) {
		
		return ResponseEntity.ok(service.authenticate(request));
	}
}
