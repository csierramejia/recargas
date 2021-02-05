package com.recargas.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.recargas.service.IOperador;


/**
 * 
 * @author
 *
 */
@Service
public class OperadorFactory {

	/** Objeto que almacena las implementaciones. */
	private ConcurrentHashMap<Integer, Class<?>> operadores;

	/** Instancia de la clase */
	private static OperadorFactory instancia;

	private EntityManager entityManager;
	
	/**
	 * Constructor privado para definir el patrón Singleton al momento de
	 * obtener la instancia de la clase.
	 */
	private OperadorFactory() {
		operadores = new ConcurrentHashMap<Integer, Class<?>>();
	}
	
	/**
	 * 
	 * @param entityManager
	 * @return
	 */
	public static OperadorFactory getInstance(EntityManager entityManager) {
		if (instancia == null) {
			instancia = new OperadorFactory();
			instancia.setEntityManager(entityManager);
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
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public IOperador obtenerOperador(int idOperador) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		Class<?> clase;
		
		if (operadores.containsKey(idOperador)) {
			clase = operadores.get(idOperador);
		}else {
			clase = Class.forName(consultarOperador(idOperador));
			operadores.put(idOperador, clase);
		}
		
		return (IOperador) clase.getDeclaredConstructor().newInstance();
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
		
		String claseImplementacion = "";
		
		Query qBd = entityManager.createNativeQuery(
				"select clase_implementacion from operadores_recargas where id_operador = ?");
		
		claseImplementacion = (String) qBd.setParameter(1, idOperador)
				.getSingleResult();
		
		return claseImplementacion;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
