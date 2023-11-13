package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Id;

public class Jugades {

	@Id
	private long id;
	private List<Daus> partides;
	
	
	public Jugades () {
	partides = new ArrayList<Daus>();
	}
	
	
	public Jugades (List<Daus> partides) {
		this.partides = partides;
	}
	
	
	public Jugades (long id, List<Daus> partides) {
		this.id = id;
		this.partides = partides;
	}


	public long getId() {
		return id;
	}


	public List<Daus> getPartides() {
		return partides;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setPartides(List<Daus> partides) {
		this.partides = partides;
	}

	
	public int percentatge() {
		int partidesGuanyades = 0;
		List<Daus> partides = getPartides();
		
		for (Daus dau : partides) {
			partidesGuanyades = (dau.victoria())?partidesGuanyades++:partidesGuanyades;
		}
		
		return (100/(partides.size()) * partidesGuanyades);	
	}
	
	public int victories() {
		int partidesGuanyades = 0;
		
		for (Daus dau : getPartides()) {
			partidesGuanyades = (dau.victoria())?partidesGuanyades++:partidesGuanyades;
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
	
	@Override
	public String toString() {
		return "Partides guanyades = "  + victories() + "  Partides perdudes: " + (getPartides().size() - victories());
	}
	
	
}
