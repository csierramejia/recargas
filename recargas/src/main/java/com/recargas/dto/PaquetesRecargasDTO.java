package com.recargas.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * paquetes_recargas
 */
@Data
public class PaquetesRecargasDTO implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long idPaquete;
    private String nombre;
    private String descripcion;
	private String descDuracion;
	private Long valor;
	private String idEstado;

}
