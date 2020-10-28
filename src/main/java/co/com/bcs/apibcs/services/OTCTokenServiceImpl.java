package co.com.bcs.apibcs.services;


import org.springframework.stereotype.Service;

import co.com.bcs.apibcs.services.dto.OTCRetiroRequest;
import co.com.bcs.apibcs.mappers.SolicitarOTCRetiroRequestTypeMapper;
import co.com.bcs.backend.services.client.samotc.SolicitarOTCRetiroRequestType;
import co.com.bcs.backend.services.client.samotc.SolicitarOTCRetiroResponseType;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OTCTokenServiceImpl implements OTCTokenService {

	private WsClientService clientService;

	private SolicitarOTCRetiroRequestTypeMapper solicitarOTCRetiroRequestTypeMapper;

	@Override
	public String solicitarToken(OTCRetiroRequest request) {
		/*
		 * // Temporal, debe removerse cabecera.getInvocador().setCanalOrigen("007");
		 * cabecera.getInvocador().setSubcanal("5");
		 * cabecera.getInvocador().setCodigoOficina("320670"); //
		 */

		SolicitarOTCRetiroRequestType requestWS = solicitarOTCRetiroRequestTypeMapper
				.toRegistroClienteRequestType(request);
		SolicitarOTCRetiroResponseType respuestaWS = clientService.solicitarOTCRetiro(requestWS);

		return respuestaWS.getExpira().toString();

	}

}