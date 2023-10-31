package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.autenticacio;

public class RegisterRequest {

	
	
	private String nom;
	
	private String contrasenya;

	public RegisterRequest(String nom, String contrasenya) {
		this.nom = nom;
		this.contrasenya = contrasenya;
	}
	
	
	public RegisterRequest() {}


	public String getNom() {
		return nom;
	}


	public String getContrasenya() {
		return contrasenya;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	
	
}
