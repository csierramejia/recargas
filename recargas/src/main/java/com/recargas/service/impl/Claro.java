package com.recargas.service.impl;

import org.springframework.stereotype.Service;

import com.recargas.dto.RegistrarRecargaDTO;
import com.recargas.dto.ResponseDTO;
import com.recargas.service.IOperador;

@Service
public class Claro implements IOperador{

	@Override
	public ResponseDTO recargar(RegistrarRecargaDTO recargaDTO) {
		System.out.println("Operador Claro");
		return null;
	}

}
