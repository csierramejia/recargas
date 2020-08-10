package com.recargas.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * paquetes_recargas
 */
@Data
public class OperadoresRecargasDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idOperador;
	private String nombre;
	private String descripcion;
	private String idEstado;

}
