package com.recargas.facade;

import org.springframework.web.client.RestTemplate;

import com.recargas.dto.ResponseDTO;
import com.recargas.dto.TransaccionDTO;

public class TransaccionFacade {

	/**
	 * Metodo encargado de consumir servicio de sistema externo
	 * @param transaccionDTO
	 * @param url
	 * @return
	 */
	public static ResponseDTO send(TransaccionDTO transaccionDTO, String url) {
	    RestTemplate restTemplate = new RestTemplate();
	    return restTemplate.postForObject(url, transaccionDTO, ResponseDTO.class);
	}
	
	public static ResponseDTO send(Long id, String url) {
	    RestTemplate restTemplate = new RestTemplate();
	    return restTemplate.postForObject(url, id, ResponseDTO.class);
	}
	
}
