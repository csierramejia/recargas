package com.recargas.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class VentaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date datePlayed;
	private Long idCustomer;
	private Long idUser;
	private Long valueBet;
	private Long valueVat;
	private Long valueBetTotal;
	private List<RegistrarRecargaDTO> recargas;
	private String canal;
	private String producto;

}
