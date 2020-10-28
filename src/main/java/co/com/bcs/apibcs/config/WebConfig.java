package co.com.bcs.apibcs.config;

import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zalando.logbook.HttpLogFormatter;
import org.zalando.logbook.Sink;

import co.com.bcs.apibcs.handler.CabeceraHandlerMethodArgumentResolver;
import co.com.bcs.apibcs.handler.DepositoIDHandlerMethodArgumentResolver;
import co.com.bcs.apibcs.services.LoggingService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor()
public class WebConfig implements WebMvcConfigurer {

	@Value("${oauth2.token.url}")
	private String urlIntrospector;

	@Value("${apibcs.role.access}")
	private String rol;

	@Value("#{servletContext.contextPath}")
	private String servletContextPath;

	@Autowired
	LocalValidatorFactoryBean localValidator;

	@Override
	public Validator getValidator() {
		return localValidator;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new CabeceraHandlerMethodArgumentResolver());
		argumentResolvers.add(new DepositoIDHandlerMethodArgumentResolver());
	}

	@Bean
	public GroupedOpenApi depositoApi() {
		return GroupedOpenApi.builder().group("Api Banco Deposito Electronicos V1")
				.packagesToScan("co.com.bcs.apibcs.controller.v1.depositoelectronico").build();

	}

	@Bean
	public OpenAPI metaData() {
		return new OpenAPI().info(new Info().title("Banco Caja Social REST API")
				.description("API REST del Banco Caja Social").version("1.0.0"));
	}

	@Bean
	public Sink sink(LoggingService loggingService , HttpLogFormatter httpLogFormatter){
		return new SinkDatabase(loggingService , httpLogFormatter);
	}
	
	
}
