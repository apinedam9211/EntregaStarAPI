package co.com.bcs.apibcs.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.bcs.apibcs.entity.ApiBCSAliado;
import co.com.bcs.apibcs.exception.BackendException;
import co.com.bcs.apibcs.mappers.TuxedoMappers;
import co.com.bcs.apibcs.repository.ApiBCSAliadoRepository;
import co.com.bcs.apibcs.services.ConsumoTuxedoService;
import co.com.bcs.apibcs.services.TransaccionesFinancierasServiceImpl;
import co.com.bcs.apibcs.services.dto.CreditoDepositoElectronicoRequest;
import co.com.bcs.apibcs.tuxedo.objects.CreditoTuxedoRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;

public class TranssaccionesFinancierasServiceImplTest {

    TransaccionesFinancierasServiceImpl service;
    @Mock
    ApiBCSAliadoRepository apiBCSAliadoRepository;
    @Mock
    TuxedoMappers tuxedoMappers;
    @Mock
    ConsumoTuxedoService tuxedoService;

    private static final String SERVICIO_RETIRO = "ISOCBP_RETIROAB";
    private static final String SERVICIO_CREDITO = "ISOCBP_RECARGAS";

    private String tramaER = "ERRGN05605NO EXISTE PDS PARA EL CB"+
    "                                          89                            "+
    "000000000000000000000000000000000000                            ";
    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        service = new TransaccionesFinancierasServiceImpl(apiBCSAliadoRepository, tuxedoMappers, tuxedoService);
    }

    @Test
    void acreditarOK() throws Exception {

        setUserAuth();
        
        CreditoDepositoElectronicoRequest request = new CreditoDepositoElectronicoRequest();
        
        when(tuxedoMappers.toCreditoTuxedoRequest(request)).thenReturn(new CreditoTuxedoRequest());
        when(apiBCSAliadoRepository.findByNombreAliado(any())).thenReturn(Optional.of(new ApiBCSAliado()));
        when(tuxedoService.invoke(eq(SERVICIO_CREDITO), any())).thenReturn(tramaER);
        
        assertThrows(BackendException.class  ,() ->service.credito(request));


    }

    private void setUserAuth() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("attr1", "value");
        DefaultOAuth2AuthenticatedPrincipal defaultOAuth2AuthenticatedPrincipal = new DefaultOAuth2AuthenticatedPrincipal(
                "user", attributes, null);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(defaultOAuth2AuthenticatedPrincipal);
    }

}