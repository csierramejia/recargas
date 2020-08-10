package com.recargas.entity;

import java.io.Serializable;

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
 * paquetes_recargas
 */
@Data
@Entity
@Table(name = "paquetes_recargas")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PaquetesRecargas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
   	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id_paquete")
    private Long idPaquete;
	@Column(name = "nombre")
    private String nombre;
	@Column(name = "descripcion")
    private String descripcion;
	@Column(name = "desc_duracion")
	private String descDuracion;
	@Column(name = "valor")
	private Long valor;
	@Column(name = "id_estado")
	private String idEstado;
	
 }
