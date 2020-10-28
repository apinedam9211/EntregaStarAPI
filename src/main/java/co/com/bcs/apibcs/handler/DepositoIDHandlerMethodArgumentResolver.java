package co.com.bcs.apibcs.handler;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import co.com.bcs.apibcs.dto.v1.entities.DepositoID;

public class DepositoIDHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return parameter.getParameterType().equals(DepositoID.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        DepositoID argument = obtenerDepositoDTO(webRequest);

        if (parameter.hasParameterAnnotation(Valid.class)) {

            WebDataBinder binder = binderFactory.createBinder(webRequest, argument, "request");
            binder.validate();
            BindingResult bindingResult = binder.getBindingResult();
            if (bindingResult.getErrorCount() > 0) {
                throw new MethodArgumentNotValidException(parameter, bindingResult);
            }
        }

        return argument;
    }

    private DepositoID obtenerDepositoDTO(NativeWebRequest webRequest) {

        Map<String, String> uriTemplateVars = (Map<String, String>) webRequest
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);

        return DepositoID.fromValue(uriTemplateVars.get("id"));

    }

}