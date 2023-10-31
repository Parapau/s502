package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.autenticacio;

public class AuthenticationRequest {

	
	private String nom;
	
	private String contrasenya;
	
	
	public AuthenticationRequest (String nom, String contrasenya) {
		this.nom = nom;
		this.contrasenya = contrasenya;
	}
	
	
	
	
	public AuthenticationRequest () {}
	
	
	
	
	public String GetNom() {
		return this.nom;
	}
	
	
	public String GetContrasenya() {
		return this.contrasenya;
	}

	
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	
	
	public void setNom (String nom) {
		this.nom = nom;
	}
	
	
}
