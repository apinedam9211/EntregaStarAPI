package co.com.bcs.apibcs.services;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;

import co.com.bcs.apibcs.entity.ApiBCSAliado;
import co.com.bcs.apibcs.exception.BackendException;
import co.com.bcs.apibcs.exception.Error500Exception;
import co.com.bcs.apibcs.mappers.TuxedoMappers;
import co.com.bcs.apibcs.repository.ApiBCSAliadoRepository;
import co.com.bcs.apibcs.services.dto.CreacionMedioManejoRequest;
import co.com.bcs.apibcs.tuxedo.objects.CabeceraStarOnlineSalida;
import co.com.bcs.apibcs.tuxedo.objects.SolicitudMedioManejoRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MedioManejoServiceImpl implements MedioManejoService {

    private static final String STAR_ONLINE = "STAR_ONLINE";

    private TuxedoMappers tuxedoMappers;

    private ConsumoTuxedoService tuxedoService;

    private ApiBCSAliadoRepository apiBCSAliadoRepository;

    @Override
    public void crearMedioManejo(CreacionMedioManejoRequest creacionMedioManejo) {

        SolicitudMedioManejoRequest request = tuxedoMappers.toSolicitudMedioManejo(creacionMedioManejo);

        Optional<ApiBCSAliado> retorno = apiBCSAliadoRepository.findByNombreAliado(getPrincipalName());

        String aliado = retorno.map(ApiBCSAliado::getCodigoCorresponsal).orElseThrow(() -> {
            throw new BackendException("Validar aliado", "00003",
                    "Aliado no registrado correctamente. Contactar al administrador ");
        });
        request.setIdentificadorAliado(aliado);
        try {
            String respuesta = tuxedoService.invoke(STAR_ONLINE, request.toTrama());
            CabeceraStarOnlineSalida response = new CabeceraStarOnlineSalida(respuesta);
            if (response.isError()) {
                throw new BackendException(response.getDescriptionError(), response.getErrorCode(),
                        response.getDescriptionError());
            }
        } catch (Exception e) {
            throw new Error500Exception(e.getMessage(), "0001", "En este momento no podemos atenderlo");
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