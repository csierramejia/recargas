package com.recargas.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.recargas.entity.Recargas;

/**
 * Spring Data repository for the Apuestas entity.
 */
@Repository
public interface IRecargasRepository extends JpaRepository<Recargas, Long> {

	@Modifying
	@Query(
	  value = 
	    " INSERT INTO recargas "
	    + " (id_recarga,id_operador, id_estado, fecha, id_usuario, valor,numero_celular,hora) "
	    + " VALUES (?, ?, ?, ?, ?, ?,?,?)",
	  nativeQuery = true)
	void registrarRecarga(
			@Param("idRecarga") Long idRecarga,
			@Param("idOperador") Integer idOperador, 
			@Param("idEstado") String idEstado,
			@Param("fecha") Date fecha,
			@Param("idUsuario") Integer idUsuario,
			@Param("valor") Long valor, 
	        @Param("numeroCelular") String numeroCelular,@Param("hora") String hora);
	

	@Modifying
	@Query(
	  value = 
	    " INSERT INTO recargas "
	    + " (id_recarga,id_operador, id_estado, fecha, id_usuario, valor,numero_celular,id_paquete, hora) "
	    + " VALUES (?, ?, ?, ?, ?, ?,?,?,?)",
	  nativeQuery = true)
	void registrarRecarga(
			@Param("idRecarga") Long idRecarga,
			@Param("idOperador") Integer idOperador, 
			@Param("idEstado") String idEstado,
			@Param("fecha") Date fecha,
			@Param("idUsuario") Integer idUsuario,
			@Param("valor") Long valor, 
	        @Param("numeroCelular") String numeroCelular,
	        @Param("idPaquete") Integer idPaquete,@Param("hora") String hora);
	

	@Query(value=" SELECT valor FROM parametros "
			+ " WHERE id_parametro = :idParametro ",
		    nativeQuery = true)
	  String consultarParametro(
				@Param("idParametro") Integer idParametro
			  );
	
	@Query(value=" SELECT clase_implementacion FROM operadores_recargas "
			+ " WHERE id_operador = :idOperador ",
		    nativeQuery = true)
	  String consultarClaseImplementacion(
				@Param("idOperador") Integer idOperador
			  );
	
}