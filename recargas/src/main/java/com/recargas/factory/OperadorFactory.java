package com.recargas.factory;

import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recargas.repository.IRecargasRepository;
import com.recargas.service.IOperador;


/**
 * 
 * @author
 *
 */
@Service
public class OperadorFactory {

	/** Objeto que almacena las implementaciones. */
	private ConcurrentHashMap<Integer, IOperador> operadores;

	/** Instancia de la clase */
	private static OperadorFactory instancia;

//	@PersistenceContext
//	private EntityManager em;
	
	@Autowired
	private IRecargasRepository recargasRepository;
	
	private final Logger log = LoggerFactory.getLogger(OperadorFactory.class);
	
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
		
		log.error("obtenerOperador " + idOperador);
		if (operadores.containsKey(idOperador)) {
			log.error("containsKey " + idOperador);
		    operador = operadores.get(idOperador);	
		}else {
			log.error("else containsKey ");
			className = consultarOperador(idOperador);
			log.error("Class.forName " + className);
			operador = (IOperador)Class.forName(className).newInstance();
			log.error("operador " + operador.getClass());
			operadores.put(idOperador, operador);
			log.error("despues de put " + idOperador);
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
		
		log.error("consultarOperador repository ::: " + idOperador);
		
//		Query qBd = em.createNativeQuery("select clase_implementacion from operadores_recargas where id_operador = ?");
//		
//		String claseImplementacion = (String) qBd
//				.setParameter(1, idOperador)
//				.getSingleResult();
		
		String claseImplementacion = recargasRepository.consultarClaseImplementacion(idOperador);
		
		log.error("claseImplementacion repository " + claseImplementacion);
		System.out.println("claseImplementacion  " + claseImplementacion);
		
		return claseImplementacion;
	}
	
}
