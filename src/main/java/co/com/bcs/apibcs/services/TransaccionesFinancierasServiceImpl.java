package co.com.bcs.apibcs.services;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import co.com.bcs.apibcs.services.dto.CreditoDepositoElectronicoRequest;
import co.com.bcs.apibcs.services.dto.DebitoDepositoElectronicoRequest;
import co.com.bcs.apibcs.entity.ApiBCSAliado;
import co.com.bcs.apibcs.exception.BackendException;
import co.com.bcs.apibcs.exception.Error500Exception;
import co.com.bcs.apibcs.mappers.TuxedoMappers;
import co.com.bcs.apibcs.repository.ApiBCSAliadoRepository;
import co.com.bcs.apibcs.tuxedo.objects.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class TransaccionesFinancierasServiceImpl implements TransaccionesFinancierasService {

	private ApiBCSAliadoRepository apiBCSAliadoRepository;

	private static final String SERVICIO_RETIRO = "ISOCBP_RETIROAB";
	private static final String SERVICIO_CREDITO = "ISOCBP_RECARGAS";

	private TuxedoMappers tuxedoMappers;

	private ConsumoTuxedoService tuxedoService;

	@Override
	public String retiro(DebitoDepositoElectronicoRequest request) {

		RetiroTuxedoRequest requestWTC = tuxedoMappers.toRetiroTuxedoRequest(request);
		
		Optional<ApiBCSAliado> retorno = apiBCSAliadoRepository.findByNombreAliado(getPrincipalName());
		if (retorno.isPresent()) {
			requestWTC.setCodigoSitio(retorno.get().getCodigoSitio());
			requestWTC.setPuntoServicio(retorno.get().getPuntoServicio());
			String respuesta;
			try {
				respuesta = tuxedoService.invoke(SERVICIO_RETIRO, requestWTC.toTrama());
			} catch (Exception e) {
				throw new Error500Exception(e.getMessage(), "0001", "En este momento no podemos atenderlo");
			}

				RetiroTuxedoResponse response = new RetiroTuxedoResponse(respuesta);
				if (response.isError()) {
					throw new BackendException(response.getSalida().getDescriptionError(),
							response.getSalida().getErrorCode(), response.getSalida().getDescriptionError());
				}

				return response.getNumeroAutorizacion();
			} else {
				throw new BackendException("Validar aliado", "00003",
						"Aliado no registrado correctamente. Contactar al administrador ");
			}
	

	}

	@Override
	public String credito(CreditoDepositoElectronicoRequest request) {

		CreditoTuxedoRequest requestWTC = tuxedoMappers.toCreditoTuxedoRequest(request);

		Optional<ApiBCSAliado> retorno = apiBCSAliadoRepository.findByNombreAliado(getPrincipalName());
		if (retorno.isPresent()) {
			requestWTC.setCodigoSitio(retorno.get().getCodigoSitio());
			requestWTC.setPuntoServicio(retorno.get().getPuntoServicio());
			String respuesta;
			try {
				respuesta = tuxedoService.invoke(SERVICIO_CREDITO, requestWTC.toTrama());
			} catch (Exception e) {
				throw new Error500Exception(e.getMessage(), "0001", "En este momento no podemos atenderlo");
			}
			CreditoTuxedoResponse response =  new CreditoTuxedoResponse(respuesta);
			if (response.isError()) {
				throw new BackendException(response.getSalida().getDescriptionError(),
						response.getSalida().getErrorCode(), response.getSalida().getDescriptionError());
			}

			return response.getNumeroAutorizacion();
		} else {
			throw new BackendException("Validar aliado", "00003",
					"Aliado no registrado correctamente. Contactar al administrador ");
		}

	}

	private String getPrincipalName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof DefaultOAuth2AuthenticatedPrincipal) {
			return ((DefaultOAuth2AuthenticatedPrincipal) principal).getName();
		} else {
			return principal.toString();
		}
	}
}