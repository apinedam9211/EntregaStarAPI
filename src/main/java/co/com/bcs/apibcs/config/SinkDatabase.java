package co.com.bcs.apibcs.config;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpRequest;
import org.zalando.logbook.HttpResponse;
import org.zalando.logbook.Precorrelation;
import org.zalando.logbook.Sink;
import org.zalando.logbook.HttpLogFormatter;
import co.com.bcs.apibcs.dto.v1.AuditDTO;
import co.com.bcs.apibcs.services.LoggingService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SinkDatabase implements Sink {

    LoggingService loggingService;
    HttpLogFormatter httpLogFormatter;

    @Override
    public void write(Precorrelation precorrelation, HttpRequest request) throws IOException {
        log.debug("Log Request:" + httpLogFormatter.format(precorrelation, request));
    }

    @Override
    public void write(Correlation correlation, HttpRequest request, HttpResponse response) throws IOException {
        log.debug("Log Response" + httpLogFormatter.format(correlation, response));
        try {
            AuditDTO auditDTO = new AuditDTO();
            auditDTO.setFinal_time(correlation.getEnd().toEpochMilli());
            auditDTO.setHttpMethod(request.getMethod());
            auditDTO.setInit_time(correlation.getStart().toEpochMilli());
            auditDTO.setIp(request.getRemote());
            auditDTO.setPrincipal_name(getPrincipalName());
            auditDTO.setRequest(httpLogFormatter.format(correlation, request));
            auditDTO.setResponse(httpLogFormatter.format(correlation, response));
            auditDTO.setResult(String.valueOf(response.getStatus()));
            auditDTO.setTransaccion(request.getPath());
            loggingService.auditar(auditDTO);
        }

        catch (Exception e) {
            log.error("error al auditar", e);
        }

    }

    public String getPrincipalName() {
       
       try{
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       

        if (principal instanceof DefaultOAuth2AuthenticatedPrincipal) {
            return ((DefaultOAuth2AuthenticatedPrincipal) principal).getName();
        }
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    catch (Exception e){
        return "Unauthorized";
    }
    }

}
