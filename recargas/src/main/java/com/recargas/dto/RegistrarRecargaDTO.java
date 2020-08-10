package com.recargas.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RegistrarRecargaDTO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String numeroRecarga;
	private Long valorRecarga;
	private Integer idOperador;
	private Integer idPaquete;

}
