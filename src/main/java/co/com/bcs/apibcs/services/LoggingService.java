package co.com.bcs.apibcs.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.bcs.apibcs.dto.v1.ApiBCSServer;
import co.com.bcs.apibcs.dto.v1.AuditDTO;

public interface LoggingService {

	String logRequest(HttpServletRequest httpServletRequest, Object body);

	String logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body);
    
	void auditApiBCSLogResponse(String response, ApiBCSServer apiServerDAO, OrigenRequest origen);
	
	void auditar(AuditDTO auditDTO);
}