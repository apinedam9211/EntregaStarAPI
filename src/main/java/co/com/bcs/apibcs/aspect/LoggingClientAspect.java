package co.com.bcs.apibcs.aspect;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.bcs.apibcs.dto.v1.ApiBCSServer;
import co.com.bcs.apibcs.services.LoggingService;
import co.com.bcs.apibcs.services.OrigenRequest;
import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESRequest;
import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaRequestType;
import co.com.bcs.backend.services.client.sam.FinalizarRegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.RegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.ValidarAlcanciaRequestType;
import co.com.bcs.backend.services.client.samotc.SolicitarOTCRetiroRequestType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@Profile("weblogic")
public class LoggingClientAspect {

	@Autowired
	private LoggingService loggingService;

	@Pointcut("within(co.com.bcs.apibcs.wsclient..*)")
	public void applicationPackagePointcut() {

		// Method is empty as this is just a Pointcut, the implementations are in the
		// advices.
	}

	/**
	 * Advice that logs methods throwing exceptions.
	 *
	 * @param joinPoint join point for advice
	 * @param e         exception
	 */
	@AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");

	}

	/**
	 * Advice that logs when a method is entered and exited.
	 *
	 * @param joinPoint join point for advice
	 * @return result
	 * @throws Throwable throws IllegalArgumentException
	 */
	@Around("applicationPackagePointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		ApiBCSServer apiServerDAO = new ApiBCSServer();
		Object resultResponse = null;
		String jsonResponse = "";
		log.debug("Inicia Aspect Logging Client");
		try {
			log.debug("Transaccion: " + joinPoint.getSignature().getDeclaringTypeName());
			log.debug("Metodo: " + joinPoint.getSignature().getName());

			/* Ws Client Encriptacion */
			if (joinPoint.getSignature().getName().equalsIgnoreCase("encriptarmensaje")) {
				apiServerDAO = getEncriptarMensajeInfo(joinPoint);
			}
			/* WS Client SAM */
			if (joinPoint.getSignature().getName().equalsIgnoreCase("validardepositoelectronico")) {
				apiServerDAO = getValidarAlcanciaInfo(joinPoint);
			}
			if (joinPoint.getSignature().getName().equalsIgnoreCase("solicitardepositoelectronico")) {
				apiServerDAO = getSolicitarDepositoElectronicoInfo(joinPoint);
			}
			if (joinPoint.getSignature().getName().equalsIgnoreCase("finalizarsolicitardepositoelectronico")) {
				apiServerDAO = getFinalizarSolicitarDepositoElectronicoInfo(joinPoint);
			}
			if (joinPoint.getSignature().getName().equalsIgnoreCase("consultarmovimientosalcancia")) {
				apiServerDAO = getConsultarMovimientosAlcanciaInfo(joinPoint);
			}

			/* WS Client OTC */
			if (joinPoint.getSignature().getName().equalsIgnoreCase("solicitartokenotc")) {
				apiServerDAO = getSolicitarTokenOTCInfo(joinPoint);
			}

			log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), joinPoint.getArgs()[0].toString());

			apiServerDAO.setFecha(new Timestamp(System.currentTimeMillis()));
			apiServerDAO.setMetodo(joinPoint.getSignature().getName());
			apiServerDAO.setEstado("OK");

			ObjectMapper mapper = new ObjectMapper();
			try {
				String json = mapper.writeValueAsString(joinPoint.getArgs()[0]);
				apiServerDAO.setRequest(json);
			} catch (JsonProcessingException e) {
				apiServerDAO.setRequest(joinPoint.getArgs()[0].toString());

			}

			resultResponse = joinPoint.proceed();

			jsonResponse = getJSONResponse(resultResponse);

			log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), jsonResponse);

			loggingService.auditApiBCSLogResponse(jsonResponse, apiServerDAO, OrigenRequest.CLIENT);
			return resultResponse;
		} catch (Exception e) {

			log.error("Illegal argument in LoggingClientAspect: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e);

			apiServerDAO.setEstado("ERROR");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			apiServerDAO.setMensajeError(exceptionAsString);
			loggingService.auditApiBCSLogResponse(jsonResponse, apiServerDAO, OrigenRequest.CLIENT);
			throw e;
		}
	}

	private ApiBCSServer getEncriptarMensajeInfo(ProceedingJoinPoint joinPoint) {
		ApiBCSServer apiServerDAO = new ApiBCSServer();
		try {
			EncriptarMensajeAESRequest result1 = (EncriptarMensajeAESRequest) joinPoint.getArgs()[0];
			apiServerDAO.setNumeroId("**************");
			apiServerDAO.setTipoId("**************");
			apiServerDAO.setNumeroReferencia(result1.getCabeceraEntrada().getInvocador().getNumeroReferencia());
			log.debug("getEncriptarMensajeInfo Datos API Server DAO OK");
			return apiServerDAO;
		} catch (Exception e) {
			log.error("Error en getEncriptarMensajeInfo: " + e.toString(), e);
			return new ApiBCSServer();
		}
	}

	private ApiBCSServer getConsultarMovimientosAlcanciaInfo(ProceedingJoinPoint joinPoint) {
		ApiBCSServer apiServerDAO = new ApiBCSServer();
		try {
			ConsultarMovimientosAlcanciaRequestType result1 = (ConsultarMovimientosAlcanciaRequestType) joinPoint
					.getArgs()[0];
			apiServerDAO.setNumeroId(result1.getIdentificacion().getNumIdentificacion());
			apiServerDAO.setTipoId(result1.getIdentificacion().getTpIdentidicacion());
			apiServerDAO.setNumeroReferencia(result1.getCabeceraEntrada().getInvocador().getNumeroReferencia());
			log.debug("getConsultarMovimientosAlcanciaInfo Datos API Server DAO OK");
			return apiServerDAO;
		} catch (Exception e) {
			log.error("Error en getConsultarMovimientosAlcanciaInfo: " + e.toString(), e);
			return new ApiBCSServer();
		}
	}

	private String getJSONResponse(Object result) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			json = "";
		}
		return json;
	}

	private ApiBCSServer getSolicitarTokenOTCInfo(ProceedingJoinPoint joinPoint) {
		ApiBCSServer apiServerDAO = new ApiBCSServer();
		try {
			SolicitarOTCRetiroRequestType result1 = (SolicitarOTCRetiroRequestType) joinPoint.getArgs()[0];
			apiServerDAO.setNumeroId(result1.getIdentificacion().getNumIdentificacion());
			apiServerDAO.setTipoId(result1.getIdentificacion().getTpIdentidicacion());
			apiServerDAO.setNumeroReferencia(result1.getCabeceraEntrada().getInvocador().getNumeroReferencia());
			log.debug("getSolicitarTokenOTCInfo Datos API Server DAO OK");
			return apiServerDAO;
		} catch (Exception e) {
			log.error("Error en getSolicitarTokenOTCInfo: " + e.toString(), e);
			return new ApiBCSServer();
		}
	}

	private ApiBCSServer getValidarAlcanciaInfo(ProceedingJoinPoint joinPoint) {
		ApiBCSServer apiServerDAO = new ApiBCSServer();
		try {
			ValidarAlcanciaRequestType result1 = (ValidarAlcanciaRequestType) joinPoint.getArgs()[0];
			apiServerDAO.setNumeroId(result1.getIdentificacion().getNumIdentificacion());
			apiServerDAO.setTipoId(result1.getIdentificacion().getTpIdentidicacion());
			apiServerDAO.setNumeroReferencia(result1.getCabeceraEntrada().getInvocador().getNumeroReferencia());
			log.debug("getValidarAlcanciaInfo Datos API Server DAO OK");
			return apiServerDAO;
		} catch (Exception e) {
			log.error("Error en getValidarAlcanciaInfo: " + e.toString(), e);
			return new ApiBCSServer();
		}
	}

	private ApiBCSServer getSolicitarDepositoElectronicoInfo(ProceedingJoinPoint joinPoint) {
		ApiBCSServer apiServerDAO = new ApiBCSServer();
		try {
			RegistroClienteRequestType result1 = (RegistroClienteRequestType) joinPoint.getArgs()[0];
			apiServerDAO.setNumeroId(result1.getIdentificacion().getNumIdentificacion());
			apiServerDAO.setTipoId(result1.getIdentificacion().getTpIdentidicacion());
			apiServerDAO.setNumeroReferencia(result1.getCabeceraEntrada().getInvocador().getNumeroReferencia());
			log.debug("getSolicitarDepositoElectronicoInfo Datos API Server DAO OK");
			return apiServerDAO;
		} catch (Exception e) {
			log.error("Error en getSolicitarDepositoElectronicoInfo: " + e.toString(), e);
			return new ApiBCSServer();
		}
	}

	private ApiBCSServer getFinalizarSolicitarDepositoElectronicoInfo(ProceedingJoinPoint joinPoint) {
		ApiBCSServer apiServerDAO = new ApiBCSServer();
		try {
			FinalizarRegistroClienteRequestType result1 = (FinalizarRegistroClienteRequestType) joinPoint.getArgs()[0];
			apiServerDAO.setNumeroId(result1.getIdCliente().getNumIdentificacion());
			apiServerDAO.setTipoId(result1.getIdCliente().getTpIdentidicacion());
			apiServerDAO.setNumeroReferencia(result1.getCabeceraEntrada().getInvocador().getNumeroReferencia());
			log.debug("getFinalizarSolicitarDepositoElectronicoInfo Datos API Server DAO OK");
			return apiServerDAO;
		} catch (Exception e) {
			log.error("Error en getFinalizarSolicitarDepositoElectronicoInfo: " + e.toString(), e);
			return new ApiBCSServer();
		}
	}

}