package co.com.bcs.apibcs.services;

import java.security.Timestamp;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import co.com.bcs.apibcs.dto.v1.ApiBCSServer;
import co.com.bcs.apibcs.dto.v1.AuditDTO;
import co.com.bcs.apibcs.entity.ApiBCSLog;
import co.com.bcs.apibcs.entity.AuditAPI;
import co.com.bcs.apibcs.repository.ApiBCSLogRepository;
import co.com.bcs.apibcs.repository.AuditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class LoggingServiceImpl implements LoggingService {

	private ApiBCSLogRepository repositoryLog;

	private AuditRepository auditRepository;

	@Override
	public String logRequest(HttpServletRequest httpServletRequest, Object body) {
		StringBuilder stringBuilder = new StringBuilder();
		Map<String, String> parameters = buildParametersMap(httpServletRequest);

		stringBuilder.append("REQUEST ");
		stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
		stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
		stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");

		if (!parameters.isEmpty()) {
			stringBuilder.append("parameters=[").append(parameters).append("] ");
		}

		if (body != null) {
			stringBuilder.append("body=[" + body + "]");
		}
		log.error(stringBuilder.toString());
		return stringBuilder.toString();
	}

	@Override
	public String logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object body) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("RESPONSE ");
		stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
		stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
		stringBuilder.append("responseHeaders=[").append(buildHeadersMap(httpServletResponse)).append("] ");
		stringBuilder.append("responseBody=[").append(body).append("] ");
		log.error(stringBuilder.toString());
		return stringBuilder.toString();
	}

	private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
		Map<String, String> resultMap = new HashMap<>();
		Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			String value = httpServletRequest.getParameter(key);
			resultMap.put(key, value);
		}

		return resultMap;
	}

	private Map<String, String> buildHeadersMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}

	private Map<String, String> buildHeadersMap(HttpServletResponse response) {
		Map<String, String> map = new HashMap<>();

		Collection<String> headerNames = response.getHeaderNames();
		for (String header : headerNames) {
			map.put(header, response.getHeader(header));
		}

		return map;
	}

	@Override
	public void auditApiBCSLogResponse(String response, ApiBCSServer apiServerDAO, OrigenRequest origen) {
		try {
			log.debug("auditApiBCSLogResponse Inicia");
			ApiBCSLog apiLog = new ApiBCSLog();
			apiLog.setFecha(apiServerDAO.getFecha());
			apiLog.setMetodo(apiServerDAO.getMetodo());
			apiLog.setNumeroId(apiServerDAO.getNumeroId());
			apiLog.setNumeroReferencia(apiServerDAO.getNumeroReferencia());
			apiLog.setOrigen(origen.name());
			apiLog.setRequest(apiServerDAO.getRequest());
			apiLog.setTipoId(apiServerDAO.getTipoId());
			apiLog.setResponse(response);
			apiLog.setEstado(apiServerDAO.getEstado());
			apiLog.setMensajeError((apiServerDAO.getMensajeError() != null) ? apiServerDAO.getMensajeError() : "OK");
			log.debug("Inicia proceso de guardado de objeto auditoria en BD");
			repositoryLog.save(apiLog);
			log.debug("Finaliza proceso de guardado de objeto auditoria en BD");
		} catch (Exception e) {
			log.error("ERROR EN AUDITAR PETICIÓN SERVICIO EN BD: " + e.toString(), e);
		}
	}

	@Override
	public void auditar(AuditDTO auditDTO) {
		LoggingServiceImpl.log.error("SE AUDITA TRANSACCION API_BCS_AUDIT");
		final AuditAPI audit = new AuditAPI();
		audit.setError_message(auditDTO.getError_message());
		audit.setFinal_time(new java.sql.Timestamp(auditDTO.getFinal_time()));
		audit.setHttpMethod(auditDTO.getHttpMethod());
		audit.setInit_time(new java.sql.Timestamp(auditDTO.getInit_time()));
		audit.setIp(auditDTO.getIp());
		audit.setPrincipal_name(auditDTO.getPrincipal_name());
		audit.setRequest(auditDTO.getRequest());
		audit.setResponse(auditDTO.getResponse());
		if (null == auditDTO.getError_message()) {
			audit.setResult(Character.valueOf('1'));
		} else {
			audit.setResult(Character.valueOf('0'));
		}
		audit.setTransaccion(auditDTO.getTransaccion());
		LoggingServiceImpl.log.error("GUARDA DATOS EN AUDITORIA");
		this.auditRepository.save(audit);
		LoggingServiceImpl.log.error("FINALIZA GUARDADO DATOS EN AUDITORIA");
	}
}
