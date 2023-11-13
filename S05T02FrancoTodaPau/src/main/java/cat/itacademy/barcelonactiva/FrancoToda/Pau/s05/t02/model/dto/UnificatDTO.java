package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.dto;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain.Daus;
import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain.Jugades;
import cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain.Jugador;

public class UnificatDTO implements Comparable<UnificatDTO>{

	private long ID;
	
	private String nom;
	
	private List<Daus> partides;
	
	
	public UnificatDTO (long ID, String nom, List<Daus> partides) {
		this.ID = ID;
		this.nom = nom;
		this.partides = partides;
	}
	
	
	public UnificatDTO (String nom) {
		this.ID = -1;
		this.nom = nom;
		this.partides = new ArrayList<Daus>();
	}
	
	public UnificatDTO () {
		this.ID = -1;
		this.nom = "Anonim";
		this.partides = new ArrayList<Daus>();
	}


	public long getID() {
		return ID;
	}


	public String getNom() {
		return nom;
	}


	public List<Daus> getPartides() {
		return partides;
	}


	public void setID(long iD) {
		ID = iD;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public void setPartidesGuanyades(List<Daus> partides) {
		this.partides= partides;
	}	
	
	
	public Daus jugar() {
		Daus daus = Daus.juga();
	
		this.partides.add(daus);
		
		return daus;
	}
	
	
	public int percentatge() {
		int partidesGuanyades = 0;
		int partidesTamany = (partides.size()==0)?1:partides.size();
		List<Daus> partides = getPartides();
		
		for (Daus dau : partides) {
			partidesGuanyades = (dau.victoria())?++partidesGuanyades:partidesGuanyades;
		}
		
		return (100/(partidesTamany) * partidesGuanyades);	
	}
	
	
	public int victories() {
		int partidesGuanyades = 0;
		
		for (Daus dau : getPartides()) {
			partidesGuanyades = (dau.victoria())?++partidesGuanyades:partidesGuanyades;
		}
		
		return partidesGuanyades;
	}
	
	
	public List<String> resultats() {
		ArrayList<String> retorn = new ArrayList<String>();

		for (Daus dau : getPartides()) {
			retorn.add(dau.toString());
		}		
		
		return retorn;
	}
	
	
	public static UnificatDTO unificar (Jugador jugador, Optional<Jugades> jugades) {
		UnificatDTO retorn;
		Jugades backup;
		
		if (jugades.isPresent()) {
			retorn = new UnificatDTO (jugador.getID(), jugador.getNomJugador(), jugades.get().getPartides());
		} else {
			backup = new Jugades();
			retorn = new UnificatDTO (jugador.getID(), jugador.getNomJugador(), backup.getPartides());

		}

		return retorn;
	}
	
	public static UnificatDTO unificar (Jugador jugador, Jugades jugades) {
		UnificatDTO retorn;

		retorn = new UnificatDTO (jugador.getID(), jugador.getNomJugador(), jugades.getPartides());

		return retorn;
	}
	
	
	
	public Object[] separar () {
		
		Jugador jugador;
		Jugades jugades;
		
		if (getID()== -1) {
			jugador = new Jugador (getNom());
			jugades = new Jugades (jugador.getID(), getPartides());
		} else {
			jugador = new Jugador (getID(), getNom());
			jugades = new Jugades (getID(), getPartides());
			
		}
		
		Object[] retorn = {jugador, jugades};
		
		return retorn;
	}
	
	
	public String nomesPercent() {
		return "Id: " + getID() + " Nom: " + getNom() + " Percentatge Victories: " + percentatge();
	}
	
	
	@Override
	public String toString() {
		String retorn = "ID: " +  getID() + " Nom = " + getNom() + " Partides guanyades = "  + victories() + "  Partides perdudes: " + (getPartides().size() - victories()) + " Percentatge de victories: " + percentatge() + "%";									
		
		for (String resultat : resultats()) {
			retorn += "	\n	" + resultat;//no se perque no funciona el \n :(	
		}
		
		return retorn;
	}


	@Override
	public int compareTo(UnificatDTO dto) {
		return this.percentatge() - dto.percentatge();
	}


	
	
	
	
	
	
	
	
	
	
	
	
}


































