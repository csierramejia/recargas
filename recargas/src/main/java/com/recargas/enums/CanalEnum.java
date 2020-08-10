package com.recargas.enums;

import com.recargas.constantes.Numero;

/**
 * Enumeracion para estados
 * @author htaborda
 *
 */
public enum CanalEnum {


	MOVIL("MOVIL", Numero.UNO.valueI),
	WEB("WEB", Numero.DOS.valueI);
	
	;

	public final String canal;
	public final Integer canalNumero;
	private CanalEnum(String canal, Integer canalNumero) {
		this.canal = canal;
		this.canalNumero = canalNumero;
	}
}
