package com.recargas.constantes;

import com.recargas.enums.EstadoEnum;

/**
 * Clase constante que contiene los DMLs Y DDLs para las consultas nativas
 */
public class SQLConstant {

	/** SQL para obtener los datos personales del usuario con base a sus credenciales*/
	public static final String GET_USER_AUTH =
		  "SELECT "
			+ "U.ID_USUARIO,"
			+ "P.PRIMER_NOMBRE,"
			+ "P.SEGUNDO_NOMBRE,"
			+ "P.PRIMER_APELLIDO,"
			+ "P.SEGUNDO_APELLIDO "
		+ "FROM PERSONAS P "
		+ "JOIN USUARIOS U ON(U.ID_USUARIO=P.ID_PERSONA)"
		+ "WHERE U.CLAVE=? "
		+ "AND U.NOMBRE_USUARIO=? "
		+ "AND U.ID_ESTADO=?";

	/** SQL para obtener los items del menu parametrizados en el sistema*/
	public static final String GET_ITEMS_MENU =
			"SELECT "
			  + "REP.ID_RECURSO AS ID_ITEM_PADRE,"
			  + "REP.NOMBRE AS NOMBRE_ITEM_PADRE,"
			  + "REP.DESCRIPCION AS DESC_ITEM_PADRE,"
			  + "RE.ID_RECURSO AS ID_ITEM,"
			  + "RE.NOMBRE AS NOMBRE_ITEM,"
			  + "RE.DESCRIPCION AS DESC_ITEM,"
			  + "RE.URL AS URL_ITEM,"
			  + "ACC.ID_ESTADO AS ESTA_ACCION, "
			  + "ACC.ID_ACCION AS ID_ACCION,"
			  + "ACC.NOMBRE AS NOMBRE_ACCION, "
			  + "RE.ICONO AS ICONO_HIJO, "
			  + "REP.ICONO AS ICONO_PADRE "
		+ "FROM ROLES_RECURSOS_ACCIONES RRA "
		+ "JOIN ROLES RO ON(RO.ID_ROL=RRA.ID_ROL)"
		+ "JOIN RECURSOS RE ON(RE.ID_RECURSO=RRA.ID_RECURSO)"
		+ "JOIN ACCIONES ACC ON(ACC.ID_ACCION=RRA.ID_ACCION)"
		+ "JOIN RECURSOS REP ON(REP.ID_RECURSO=RE.ID_RECURSO_PADRE)"
		+ "WHERE RO.ID_ROL IN(SELECT URE.ID_ROL FROM USUARIOS_ROLES_EMPRESAS URE WHERE URE.ID_USUARIO=? AND URE.ID_ESTADO=?)"
		+ "AND RE.ID_APLICACION=? "
		+ "AND RO.ID_ESTADO=? "
		+ "AND RRA.ID_ESTADO=? "
		+ "ORDER BY REP.NOMBRE ASC, RE.NOMBRE ASC";
	
	public static final String SELECT_BASE_LOTERIAS_CHANCE ="SELECT L.ID_LOTERIA,L.CODIGO,L.NOMBRE,L.NOMBRE_CORTO,L.TELEFONO,L.ID_ESTADO,S.ID_SORTEO, S.ID_SORTEO_DETALLE, S.HORA_SORTEO, L.nombre_imagen FROM SORTEOS_DETALLES S "
			+ "INNER JOIN SORTEOS SO ON (SO.id_sorteo=S.id_sorteo AND SO.id_estado=:estado) "
			+ "INNER JOIN LOTERIAS L ON (L.id_loteria=S.id_loteria AND L.id_estado=:estado) "
			+ "INNER JOIN EMPRESAS_PRODUCTOS_LOTERIAS EL ON (L.id_loteria=EL.id_loteria AND EL.id_producto=:producto AND EL.id_estado=:estado) "
			+ "WHERE S.fecha_sorteo=:fecha_sorteo  ORDER BY L.NOMBRE";

	public static final String SELECT_BASE_LOTERIAS_CHANCE_CODIGOS=" AND L.CODIGO IN (:codigos)";
	public static final String SELECT_IMPUESTOS = "SELECT i FROM Impuestos i WHERE i.nombre=:nombre";

	public static final String SELECT_BASE_CLIENTES = "SELECT C.id_persona, C.numero_documento, C.id_tipo_documento, C.primer_nombre, C.segundo_nombre, C.primer_apellido, C.segundo_apellido, C.correo_electronico, C.celular FROM PERSONAS C "
			+ " INNER JOIN CLIENTES CL ON CL.ID_CLIENTE=C.ID_PERSONA WHERE C.numero_documento=:numero AND CL.id_estado=:estado AND C.id_tipo_documento=:tipoId";
	
	public static final String SELECT_MODALIDADES_PRODUCTOS = "SELECT MP.VALOR FROM MODALIDADES_PRODUCTOS MP " + 
			"INNER JOIN PRODUCTOS P ON (MP.ID_PRODUCTO=P.ID_PRODUCTO " + 
			"AND UPPER(P.NOMBRE) =:nombreProducto)  AND MP.ID_TIPO_JUEGO =:tipoJuego ORDER BY MP.VALOR ASC";
	
	public static final String SELECT_SIGNOS = "SELECT s FROM SignosZodiacales s WHERE s.idEstado=:estado";
	
	public static final String SELECT_PAQUETES = "SELECT s FROM PaquetesRecargas s WHERE s.idEstado=:estado";
	
	public static final String SELECT_OPERADORES = "SELECT s FROM OperadoresRecargas s WHERE s.idEstado=:estado";
	
	/** Se utiliza para verificar sin un usuario tiene programacion*/
	public static final String GET_PROGRAMACION_VENDEDOR = "SELECT EXISTS( SELECT  1 " 
			+ "FROM  HORARIOS_VENDEDORES HV JOIN HORARIOS_VENDEDORES_DETALLES HVD ON (HV.ID_HORARIO_VENDEDOR = HVD.ID_HORARIO_VENDEDOR) JOIN HORARIOS HO ON(HO.CODIGO_HORA=HVD.HORA) "
			+ "WHERE HV.ID_VENDEDOR =:idUsuario AND TO_CHAR(HVD.DIA,'YYYY-MM-DD') = TO_CHAR(CURRENT_DATE,'YYYY-MM-DD') " 
			+ "AND HO.HORA_INICIAL <= to_char(timezone('GMT 5', CURRENT_TIMESTAMP), 'HH24:MI:SS') AND to_char(timezone('GMT 5', CURRENT_TIMESTAMP), 'HH24:MI:SS') <= HO.HORA_FINAL "
			+ "AND HV.ID_ESTADO='" + EstadoEnum.ACTIVO.name()
			+ "'AND HVD.ID_ESTADO='" + EstadoEnum.ACTIVO.name()
			+ "'AND HO.ID_ESTADO='"+ EstadoEnum.ACTIVO.name() +"')";
	
	/** Se utiliza para verificar sin un usuario tiene rol administrador*/
	public static final String GET_ROL_PROGRAMACION="SELECT EXISTS( SELECT  1 FROM  USUARIOS_ROLES_EMPRESAS UR JOIN ROLES RO ON(RO.ID_ROL=UR.ID_ROL) WHERE UR.ID_USUARIO =:idUsuario AND RO.ID_PROGRAMACION = '"+ EstadoEnum.ACTIVO.name() +"')";
	

	

}
