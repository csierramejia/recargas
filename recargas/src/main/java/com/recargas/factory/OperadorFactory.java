package com.recargas.factory;

import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import com.recargas.service.IOperador;

/**
 * 
 * @author
 *
 */
@Component
public class OperadorFactory {

	/** Objeto que almacena las implementaciones. */
	private ConcurrentHashMap<Integer, IOperador> operadores;

	/** Instancia de la clase */
	private static OperadorFactory instancia;

	@PersistenceContext
	private EntityManager em;
	
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
	public IOperador obtenerOperador(int idOperador) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		String className;
		IOperador operador;
		
		if (operadores.containsKey(idOperador)) {
		    operador = operadores.get(idOperador);	
		}else {
			className = consultarOperador(idOperador);
			operador = (IOperador)Class.forName(className).newInstance();
			operadores.put(idOperador, operador);
		}
		
		return operador;
	}
	
	/**
	 * 
	 * @param idOperador
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private String consultarOperador(int idOperador) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Query qBd = em.createNativeQuery("select clase_implementacion from operadores_recargas where id_operador = ?");
		
		String claseImplementacion = (String) qBd
				.setParameter(1, idOperador)
				.getSingleResult();
		
		System.out.println("claseImplementacion  " + claseImplementacion);
		
		return claseImplementacion;
	}
	
}
