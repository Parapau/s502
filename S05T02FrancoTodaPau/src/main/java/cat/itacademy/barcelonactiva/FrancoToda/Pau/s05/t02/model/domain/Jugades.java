package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain;

import jakarta.persistence.Id;

public class Jugades {

	@Id
	private long id;
	
	
	private int partidesGuanyades;
	private int partidesPerdudes;
	
	
	
	public Jugades () {
		this.partidesGuanyades = 0;
		this.partidesPerdudes = 0;
	}
	
	
	public Jugades (int partidesGuanyades, int partidesPerdudes) {
		this.partidesGuanyades = partidesGuanyades;
		this.partidesPerdudes = partidesPerdudes;
	}
	
	
	public Jugades (long id, int partidesGuanyades, int partidesPerdudes) {
		this.id = id;
		this.partidesGuanyades = partidesGuanyades;
		this.partidesPerdudes = partidesPerdudes;
	}


	public long getId() {
		return id;
	}


	public int getPartidesGuanyades() {
		return partidesGuanyades;
	}


	public int getPartidesPerdudes() {
		return partidesPerdudes;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setPartidesGuanyades(int partidesGuanyades) {
		this.partidesGuanyades = partidesGuanyades;
	}


	public void setPartidesPerdudes(int partidesPerdudes) {
		this.partidesPerdudes = partidesPerdudes;
	}
	
	
	
	public int percentatge() {
		return (100/(getPartidesGuanyades() + getPartidesPerdudes()) * getPartidesGuanyades());	
	}
	
	
	
	
	@Override
	public String toString() {
		return "Partides guanyades = "  + getPartidesGuanyades() + "  Partides perdudes: " + getPartidesPerdudes();
	}
	
	
}
