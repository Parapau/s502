package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Jugador {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)		
	private long pk_SucursalID;

	@Column(columnDefinition = "varchar(255)")
	private String nomJugador = "Anonim";

	@Column(name = "pais")
	private String paisSucursal;

}
