package com.recargas.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recargas.builder.Builder;
import com.recargas.constantes.ParametrosConstants;
import com.recargas.constantes.SQLConstant;
import com.recargas.dto.OperadoresRecargasDTO;
import com.recargas.dto.PaquetesRecargasDTO;
import com.recargas.dto.RegistrarRecargaDTO;
import com.recargas.dto.ResponseDTO;
import com.recargas.dto.TransaccionDTO;
import com.recargas.dto.VentaDTO;
import com.recargas.entity.OperadoresRecargas;
import com.recargas.entity.PaquetesRecargas;
import com.recargas.enums.CanalEnum;
import com.recargas.enums.EstadoEnum;
import com.recargas.enums.ProductosEnum;
import com.recargas.facade.TransaccionFacade;
import com.recargas.repository.IRecargasRepository;
import com.recargas.util.BusinessException;
import com.recargas.factory.OperadorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class RecargasService {

	/** Contexto de la persistencia del sistema */
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IRecargasRepository recargasRepository;
	
	private final Logger log = LoggerFactory.getLogger(RecargasService.class);
	
	Builder<PaquetesRecargas, PaquetesRecargasDTO> builderDTOPaque = new Builder<PaquetesRecargas,PaquetesRecargasDTO>(PaquetesRecargasDTO.class);
	/**
	 * Builder para pasar de DTO a entidad
	 */
	Builder<PaquetesRecargasDTO, PaquetesRecargas> builderEntidadPaque = new Builder<PaquetesRecargasDTO, PaquetesRecargas>(PaquetesRecargas.class);
	
	
	Builder<OperadoresRecargas, OperadoresRecargasDTO> builderDTOOpe = new Builder<OperadoresRecargas,OperadoresRecargasDTO>(OperadoresRecargasDTO.class);
	/**
	 * Builder para pasar de DTO a entidad
	 */
	Builder<OperadoresRecargasDTO, OperadoresRecargas> builderEntidadOpe = new Builder<OperadoresRecargasDTO, OperadoresRecargas>(OperadoresRecargas.class);
	


    @Transactional
	public ResponseDTO registrarRecarga(VentaDTO registrarVentaDTO) {
    	ResponseDTO response =null;
    	try {
    		TransaccionDTO apuestaDTO = new TransaccionDTO();
		if(registrarVentaDTO.getCanal()!=null && registrarVentaDTO.getCanal().equals(CanalEnum.MOVIL.canal)) {
		apuestaDTO.setIdCanal(CanalEnum.MOVIL.canalNumero);
		}
		else {
			apuestaDTO.setIdCanal(CanalEnum.WEB.canalNumero);
		}
		apuestaDTO.setFecha(registrarVentaDTO.getDatePlayed());
		SimpleDateFormat horaF = new SimpleDateFormat("HH:mm:ss");
		///primero validamos si alguna de las loterias ya cerro
		Query qBd=em.createNativeQuery("select to_char(timezone('GMT 5'\\:\\:text, CURRENT_TIMESTAMP), 'HH24:MI:SS'\\:\\:text)");
		String horaBD=(String) qBd.getSingleResult();
		////
		Date horaBDC=new Date();
		try {
			horaBDC = horaF.parse(horaBD);
		} catch (ParseException e1) {
			
		}
		apuestaDTO.setHora(horaF.format(horaBDC));
		if(registrarVentaDTO.getIdCustomer() != null) {
			apuestaDTO.setIdCliente(registrarVentaDTO.getIdCustomer().intValue());
		}
		apuestaDTO.setIdDispositivo(1);
		apuestaDTO.setIdEmpresa(1);
		apuestaDTO.setIdEstado(EstadoEnum.EXITOSO.name());
		apuestaDTO.setIdProducto(ProductosEnum.productoPorString(registrarVentaDTO.getProducto()));
		apuestaDTO.setIdTipoTransaccion(1);
		apuestaDTO.setLatitud(null);
		apuestaDTO.setLongitud(null);
		apuestaDTO.setValorTransaccion(registrarVentaDTO.getValueBetTotal());
		apuestaDTO.setIdUsuario(registrarVentaDTO.getIdUser().intValue());
		
		if(registrarVentaDTO.getRecargas()!=null && !registrarVentaDTO.getRecargas().isEmpty()) {
			response=recarga(registrarVentaDTO,apuestaDTO);
		}
		
    	}
    	catch (Exception e) {
			if(response==null) {
				response= new ResponseDTO();
			}
			response.setExito(Boolean.FALSE);
		}
		return response;
	}
    
    
    private ResponseDTO recarga(VentaDTO registrarVentaDTO, TransaccionDTO apuestaDTO) {
		ResponseDTO response =new ResponseDTO();
		
		try {
			Date fechaBD = new Date();
			// fecha BD
			Query qBd = em.createNativeQuery("select to_char(timezone('GMT 5'\\:\\:text, CURRENT_TIMESTAMP), 'dd/mm/yyyy'\\:\\:text)");
				String fechaChar=(String) qBd.getSingleResult();
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				fechaBD = format.parse(fechaChar);
		
			for (RegistrarRecargaDTO rec : registrarVentaDTO.getRecargas()) {
				 response = TransaccionFacade.send(apuestaDTO,
						 recargasRepository.consultarParametro(ParametrosConstants.REGISTRAR_TRANSACCION));
				if(rec.getIdPaquete()!=null) {
				recargasRepository.registrarRecarga(response.getIdTransaccion(),rec.getIdOperador(), EstadoEnum.ACTIVO.name(),fechaBD,
						registrarVentaDTO.getIdUser().intValue(), rec.getValorRecarga(), rec.getNumeroRecarga(),rec.getIdPaquete());
				}
				else {
					recargasRepository.registrarRecarga(response.getIdTransaccion(),rec.getIdOperador(), EstadoEnum.ACTIVO.name(),fechaBD,
							registrarVentaDTO.getIdUser().intValue(), rec.getValorRecarga(), rec.getNumeroRecarga());
				}
				
				apuestaDTO.setIdTransaccion(response.getIdTransaccion());
				
				TransaccionFacade.send(apuestaDTO,
						recargasRepository.consultarParametro(ParametrosConstants.CONFIRMAR_TRANSACCION));
				
				// Se envia la transaccion al operador
				OperadorFactory factory = OperadorFactory.getInstance(em);
				IOperador operador = factory.obtenerOperador(rec.getIdOperador());
				operador.recargar(rec);
			}
		}
		catch(Exception e) {
			log.error(e.getMessage());
			response.setExito(Boolean.FALSE);
			response.setMensaje(e.getMessage());
			return response;
		}
	
		response.setExito(Boolean.TRUE);
		return response;
		
	}

		
	public List<PaquetesRecargasDTO> consultarPaquetes() throws BusinessException {
		Query q = em.createQuery(SQLConstant.SELECT_PAQUETES).setParameter("estado", EstadoEnum.ACTIVO.name());
		List<PaquetesRecargas> signosZodiacales = q.getResultList();
		List<PaquetesRecargasDTO> salida = builderDTOPaque.copy(signosZodiacales);
		return salida;
	}
	
	public List<OperadoresRecargasDTO> consultarOperadores() throws BusinessException {
		Query q = em.createQuery(SQLConstant.SELECT_OPERADORES).setParameter("estado", EstadoEnum.ACTIVO.name());
		List<OperadoresRecargas> signosZodiacales = q.getResultList();
		List<OperadoresRecargasDTO> salida = builderDTOOpe.copy(signosZodiacales);
		return salida;
	}
	
}