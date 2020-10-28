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
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.bcs.apibcs.dto.v1.ApiBCSServer;
import co.com.bcs.apibcs.services.LoggingService;
import co.com.bcs.apibcs.services.OrigenRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingTuxedoAspect {

	@Autowired
	private LoggingService loggingService;

	@Pointcut("this(co.com.bcs.apibcs.services.ConsumoTuxedoService)")
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
		log.debug("Inicia Aspect Logging Tuxedo");
		try {
			log.debug("Transaccion: " + joinPoint.getSignature().getDeclaringTypeName());

			apiServerDAO = getMensajeTuxedo(joinPoint);

			log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), apiServerDAO.getRequest());

			apiServerDAO.setFecha(new Timestamp(System.currentTimeMillis()));
			apiServerDAO.setEstado("OK");
			resultResponse = joinPoint.proceed();
			jsonResponse = getJSONResponse(resultResponse);

			log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), jsonResponse);

			loggingService.auditApiBCSLogResponse(jsonResponse, apiServerDAO, OrigenRequest.TUXEDO);
			return resultResponse;
		} catch (Exception e) {

			log.error("Illegal argument in LoggingTuxedoAspect: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
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

	private ApiBCSServer getMensajeTuxedo(ProceedingJoinPoint joinPoint) {
		ApiBCSServer apiServerDAO = new ApiBCSServer();
		try {
			String service = (String) joinPoint.getArgs()[0];
			String payload = (String) joinPoint.getArgs()[1];
			apiServerDAO.setNumeroId("*****");
			apiServerDAO.setTipoId("*****");
			apiServerDAO.setMetodo(service);
			apiServerDAO.setNumeroReferencia("*****");
			apiServerDAO.setRequest(payload);
			log.debug("Metodo: " + service);
			log.debug("getEncriptarMensajeInfo Datos API Server DAO OK");
			return apiServerDAO;
		} catch (Exception e) {
			log.error("Error en getEncriptarMensajeInfo: " + e.toString(), e);
			return new ApiBCSServer();
		}
	}
}
