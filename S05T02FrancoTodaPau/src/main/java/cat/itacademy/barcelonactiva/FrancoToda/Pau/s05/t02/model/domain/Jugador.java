package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Jugador implements Comparable<Jugador> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)		
	private long ID;

	@Column(columnDefinition = "varchar(255) default = 'Anonim'")
	private String nomJugador;


	public Jugador() {}
	
	
	public Jugador(String nom) {
		this.nomJugador = nom;
	}
	
	
	public Jugador (long ID, String nom) {
		this.ID = ID;
		this.nomJugador = nom;
	}


	public long getID() {
		return ID;
	}


	public String getNomJugador() {
		return nomJugador;
	}


	public void setID(long iD) {
		ID = iD;
	}


	public void setNomJugador(String nomJugador) {
		this.nomJugador = nomJugador;
	}
	
	
	
	
	@Override
	public String toString() {
		return "ID: " +  getID() + ", Nom = " + getNomJugador(); 
	}


	@Override
	public int compareTo(Jugador jugador) {
		return Long.compare(getID(), jugador.getID());
	}

	
	
	
}
