package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.autenticacio;

public class AuthenticationResponse {
	
	
	private String token;

	public AuthenticationResponse(String token) {
		this.token = token;
	}

	public AuthenticationResponse() {}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	

}
