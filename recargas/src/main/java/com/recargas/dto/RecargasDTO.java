package com.recargas.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Apuesta
 */
@Data
public class RecargasDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long idRecarga;
    private Long idOperador;
    private String codigoAutorizacion;
	private String idEstado;
	private Date fecha;
	private Long idUsuario;
	private Long valor;
	private String numeroCelular;
	private String idPaquete;
	
 }
