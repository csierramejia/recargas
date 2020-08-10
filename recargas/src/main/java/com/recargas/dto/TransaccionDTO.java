package com.recargas.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * TransaccionDTO
 * Clase para encapsular la información transversal de las transacciones
 */
@Data
public class TransaccionDTO implements Serializable {

	private Long idTransaccion;
    private int idTipoTransaccion;
    Date fecha;
    String hora;
    private int idUsuario;
    private int idDispositivo;
    private int idCanal;
    private int idProducto;
    private int idEmpresa;
    private String latitud;
    private String longitud;
    private double valorTransaccion;
    private int idCliente;
    private String idEstado;
    
}
