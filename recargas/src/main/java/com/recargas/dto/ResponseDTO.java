package com.recargas.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ResponseDTO implements Serializable{

	long idTransaccion;
	boolean exito;
	Date fecha;
	String hora;	
	String codigo;
	String mensaje;
	
}
