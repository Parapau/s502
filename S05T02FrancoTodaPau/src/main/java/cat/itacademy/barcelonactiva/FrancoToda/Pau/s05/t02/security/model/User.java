package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.model;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class User {

	@Id
	private long id;
	
	private String nom;
	
	private String contrasenya;
	
	public User() {}
	
	public User(long id, String nom, String contrasenya) {
		this.id = id;
		this.nom = nom;
		this.contrasenya = contrasenya;
	}

	public long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	
	
	
	
}
