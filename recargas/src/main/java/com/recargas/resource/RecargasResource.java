package com.recargas.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recargas.dto.VentaDTO;
import com.recargas.service.RecargasService;
import com.recargas.util.BusinessException;
import com.recargas.util.Util;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Servicio que contiene todos los procesos de negocio para las Loterias
 * localhost:puerto/path_name/
 */
@RestController
@RequestMapping("/recargas/recarga")
public class RecargasResource {
	
	/**
	 * Atributo Logger para la app
	 */
	 private final Logger log = LoggerFactory.getLogger(RecargasResource.class);

	/**
	 * Atributo que representa el servicio de ClienteService
	 */
	@Autowired
	private RecargasService recargasService;
	
	@GetMapping(path = "/consultarPaquetes", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Consultar consultarPlanes", notes = "Operación para consular consultarPaquetes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Proceso ejecutado satisfactoriamente"),
			@ApiResponse(code = 400, message = "Se presentó una exception de negocio"),
			@ApiResponse(code = 404, message = "Recurso no encontrado"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Object> consultarPaquetes(){
		try {
			return Util.getResponseSuccessful(this.recargasService.consultarPaquetes());
		} catch (BusinessException e) {
			return Util.getResponseBadRequest(e.getMessage());
		} catch (Exception e) {
			return Util.getResponseError(RecargasResource.class.getSimpleName() + ".consultarPaquetes", e.getMessage());
		}
	}
	@GetMapping(path = "/consultarOperadores", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Consultar consultarOperadores", notes = "Operación para consular consultarOperadores")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Proceso ejecutado satisfactoriamente"),
			@ApiResponse(code = 400, message = "Se presentó una exception de negocio"),
			@ApiResponse(code = 404, message = "Recurso no encontrado"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Object> consultarOperadores(){
		try {
			return Util.getResponseSuccessful(this.recargasService.consultarOperadores());
		} catch (BusinessException e) {
			return Util.getResponseBadRequest(e.getMessage());
		} catch (Exception e) {
			return Util.getResponseError(RecargasResource.class.getSimpleName() + ".consultarOperadores", e.getMessage());
		}
	}
	
	  @PostMapping(path = "/registrarRecarga",
				consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ApiOperation(value = "registrarRecarga", notes = "Operación para crear la apuesta")
		@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Proceso ejecutado satisfactoriamente"),
				@ApiResponse(code = 400, message = "Se presentó una exception de negocio"),
				@ApiResponse(code = 404, message = "Recurso no encontrado"),
				@ApiResponse(code = 500, message = "Internal Server Error")})  
	    public ResponseEntity<Object> registrarRecarga(@RequestBody VentaDTO recargaDTO) throws Exception {
	    	try {
				return Util.getResponseSuccessful(this.recargasService.registrarRecarga(recargaDTO));
			} catch (Exception e) {
				return Util.getResponseError(RecargasResource.class.getSimpleName() + ".registrarRecarga ", e.getMessage());
			}
	    }
	
}
