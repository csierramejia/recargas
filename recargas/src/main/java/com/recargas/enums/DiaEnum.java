package com.recargas.enums;

import com.recargas.constantes.Numero;

/**
 * Enum para los dias de la semana y su numero correspondiente
 */
public enum DiaEnum {

	DOMINGO("Domingo", Numero.UNO.valueI.toString()),
	LUNES("Lunes", Numero.DOS.valueI.toString()),
	MARTES("Martes", Numero.TRES.valueI.toString()),
	MIERCOLES("Miercoles", Numero.CUATRO.valueI.toString()),
	JUEVES("Jueves", Numero.CINCO.valueI.toString()),
	VIERNES("Viernes", Numero.SEIS.valueI.toString()),
	SABADO("Sabado", Numero.SIETE.valueI.toString());

	public final String dia;
	public final String diaNro;
	private DiaEnum(String dia, String diaNro) {
		this.dia = dia;
		this.diaNro = diaNro;
	}
}
