package com.recargas.service;

import com.recargas.dto.RegistrarRecargaDTO;
import com.recargas.dto.ResponseDTO;

public interface IOperador {

	public ResponseDTO recargar(RegistrarRecargaDTO recargaDTO);
	
}
