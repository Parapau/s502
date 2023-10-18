package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.dto;

import java.util.Optional;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain.Jugades;
import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain.Jugador;

public class UnificatDTO {

	private long ID;
	
	private String nom;
	
	private int partidesGuanyades, partidesPerdudes;
	
	
	public UnificatDTO (long ID, String nom, int partidesGuanyades, int partidesPerdudes) {
		this.ID = ID;
		this.nom = nom;
		this.partidesGuanyades = partidesGuanyades;
		this.partidesPerdudes = partidesPerdudes;
	}
	
	
	public UnificatDTO (String nom) {
		this.ID = -1;
		this.nom = nom;
		this.partidesGuanyades = 0;
		this.partidesPerdudes = 0;
	}


	public long getID() {
		return ID;
	}


	public String getNom() {
		return nom;
	}


	public int getPartidesGuanyades() {
		return partidesGuanyades;
	}


	public int getPartidesPerdudes() {
		return partidesPerdudes;
	}


	public void setID(long iD) {
		ID = iD;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public void setPartidesGuanyades(int partidesGuanyades) {
		this.partidesGuanyades = partidesGuanyades;
	}


	public void setPartidesPerdudes(int partidesPerdudes) {
		this.partidesPerdudes = partidesPerdudes;
	}
	
	
	
	
	public static UnificatDTO unificar (Jugador jugador, Optional<Jugades> jugades) {
		UnificatDTO retorn;

		if (jugades.isPresent()) {
			retorn = new UnificatDTO (jugador.getID(), jugador.getNomJugador(), jugades.get().getPartidesGuanyades(), jugades.get().getPartidesPerdudes());
		} else {
			/*TODO*/System.err.println("Un jugador no tenia jugades, pau mira que ha passat");//aixo segurament sobra pero dem moment ho deixo per si les mosques
			Jugades backup = new Jugades();
			retorn = new UnificatDTO (jugador.getID(), jugador.getNomJugador(), backup.getPartidesGuanyades(), backup.getPartidesPerdudes());

		}

		return retorn;
	}
	
	public static UnificatDTO unificar (Jugador jugador, Jugades jugades) {
		UnificatDTO retorn;

		retorn = new UnificatDTO (jugador.getID(), jugador.getNomJugador(), jugades.getPartidesGuanyades(), jugades.getPartidesPerdudes());

		return retorn;
	}
	
	
	
	public Object[] separar () {
		
		Jugador jugador;
		Jugades jugades;
		
		if (getID()== -1) {
			jugador = new Jugador (getNom());
			jugades = new Jugades (jugador.getID(), getPartidesGuanyades(), getPartidesPerdudes());
		} else {
			jugador = new Jugador (getID(), getNom());
			jugades = new Jugades (getID(), getPartidesGuanyades(), getPartidesPerdudes());
			
		}
		
		Object[] retorn = {jugador, jugades};
		
		return retorn;
	}
	
	@Override
	public String toString() {
		return "ID: " +  getID() + ", Nom = " + getNom() + "Partides guanyades = "  + getPartidesGuanyades() + "  Partides perdudes: " + getPartidesPerdudes();
	}


	
	
	
	
	
	
	
	
	
	
	
	
}


































