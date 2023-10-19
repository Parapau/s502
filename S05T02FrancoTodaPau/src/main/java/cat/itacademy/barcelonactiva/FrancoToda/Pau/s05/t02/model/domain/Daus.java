package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model.domain;

public class Daus {

	private int dau1;
	private int dau2;
	
	
	
	public Daus(int dau1, int dau2) {//tampoc se quan podries fer servir aixo pero aqui ho tens pau del futur :)
		this.dau1 = dau1;
		this.dau2 = dau2;
	}

	
	public int getDau1() {
		return dau1;
	}

	public int getDau2() {
		return dau2;
	}

	public void setDau1(int dau1) {//no els hauries de necesitar pero aqui els tens
		this.dau1 = dau1;
	}

	public void setDau2(int dau2) {
		this.dau2 = dau2;
	}

	
	public boolean victoria () {
		boolean retorn = (dau1 + dau2 == 7)?true:false;
		return retorn;
	}
	
	public static Daus juga () {
		int dau1 = (int) Math.random()*6;
		int dau2 = (int) Math.random()*6;
		return new Daus(dau1, dau2);
	}
	
	
	@Override
	public String toString() {
		return "Dau1: " + getDau1() + " Dau: " + getDau2() + " Total: " + (getDau1() + getDau2()) + ((victoria())?"Gunayat! :D":" Perdut :(");
	}

	
	





}

