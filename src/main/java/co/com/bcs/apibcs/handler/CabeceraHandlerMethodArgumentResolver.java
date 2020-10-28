package co.com.bcs.apibcs.handler;

import javax.validation.Valid;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import co.com.bcs.apibcs.dto.v1.entities.Cabecera;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CabeceraHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public CabeceraHandlerMethodArgumentResolver() {
        log.error("Inicializando CabeceraHandlerMethodArgumentResolver");

    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Cabecera.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        log.error("resolve argument from petition" + webRequest.getContextPath());
      //  String codigoCorresponsal = webRequest.getHeader("codigo-corresponsal");
       // String codigoTerminal = webRequest.getHeader("codigo-terminal");
        String hashMessage = webRequest.getHeader("hash-message");
        String ipCliente = webRequest.getHeader("ip-cliente-corresponsal");
        String ipServidor = webRequest.getHeader("ip-servidor-corresponsal");
        String macCliente = webRequest.getHeader("mac-cliente-corresponsal");
        String macServidor = webRequest.getHeader("mac-servidor-corresponsal");
        String numeroReferencia = webRequest.getHeader("numero-referencia");
        String paisOrigen = webRequest.getHeader("pais-origen");

        Cabecera cabecera = new Cabecera(numeroReferencia, paisOrigen, ipServidor,
                macServidor, ipCliente, macCliente, hashMessage);

        if (parameter.hasParameterAnnotation(Valid.class)) {

            WebDataBinder binder = binderFactory.createBinder(webRequest, cabecera, "cabecera");
            binder.validate();
            BindingResult bindingResult = binder.getBindingResult();
            if (bindingResult.getErrorCount() > 0) {
                throw new MethodArgumentNotValidException(parameter, bindingResult);
            }
        }

        return cabecera;
    }

}