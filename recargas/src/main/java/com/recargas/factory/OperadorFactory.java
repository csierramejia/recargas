package com.recargas.factory;

import java.util.concurrent.ConcurrentHashMap;

import com.recargas.service.IOperador;

/**
 * 
 * @author
 *
 */
public class OperadorFactory {

	/** Objeto que almacena las implementaciones. */
	private ConcurrentHashMap<Integer, IOperador> operadores;

	/** Instancia de la clase */
	private static OperadorFactory instancia;

	/**
	 * Constructor privado para definir el patrón Singleton al momento de
	 * obtener la instancia de la clase.
	 */
	private OperadorFactory() {
		operadores = new ConcurrentHashMap<Integer, IOperador>();
	}
	
	public static OperadorFactory getInstance() {
		if (instancia == null) {
			instancia = new OperadorFactory();
		}
		return instancia;
	}

	/**
	 * 
	 * @param idOperador
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public IOperador obtenerOperador(int idOperador) throws InstantiationException, IllegalAccessException, ClassNotFoundException{

		IOperador operador;
		
		if (!operadores.containsKey(idOperador)) {
		    operador = operadores.get(idOperador);	
		}else {
			// consultar en la DB
			String className = "com.recargas.service.impl.Claro";
			operador = (IOperador)Class.forName(className).newInstance();
			operadores.put(idOperador, operador);
		}
		
		return operador;
	}
	
}
