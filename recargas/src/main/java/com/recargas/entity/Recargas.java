package com.recargas.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;

/**
 * Apuesta
 */
@Data
@Entity
@Table(name = "recargas")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Recargas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
   	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id_recarga")
    private Long idRecarga;
	@Column(name = "id_operador")
    private Long idOperador;
	@Column(name = "codigo_autorizacion")
    private String codigoAutorizacion;
	@Column(name = "id_estado")
	private String idEstado;
	@Column(name = "fecha")
	private Date fecha;
	@Column(name = "id_usuario")
	private Long idUsuario;
	@Column(name = "valor")
	private Long valor;
	@Column(name = "numero_celular")
	private String numeroCelular;
	@Column(name = "id_paquete")
	private String idPaquete;
	
 }
