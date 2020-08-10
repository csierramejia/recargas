package com.recargas.enums;

import com.recargas.constantes.Numero;

/**
 * Enumeracion para estados
 * @author htaborda
 *
 */
public enum ProductosEnum {


	RECARGAS("RECARGAS", Numero.UNO.valueI),
	CHANCE("CHANCE", Numero.DOS.valueI),
	LOTERIAS("LOTERIAS", Numero.TRES.valueI),
	GIROS("GIROS", Numero.CUATRO.valueI),
	CHANCE_MILLONARIO("CHANCE_MILLONARIO", Numero.CINCO.valueI),
	SUPER_CHANCE("SUPER_CHANCE", Numero.SEIS.valueI),
	SUPER_ASTRO("SUPER_ASTRO", Numero.SIETE.valueI),
	
	;

	public final String producto;
	public final Integer productoNumero;
	private ProductosEnum(String producto, Integer productoNumero) {
		this.producto = producto;
		this.productoNumero = productoNumero;
	}
	
	public static Integer productoPorString(String nombreProducto) {
		for (ProductosEnum p : ProductosEnum.values()) {
			if(p.producto.equalsIgnoreCase(nombreProducto)) {
				return p.productoNumero;
			}
		}
		return null;
	}
}
