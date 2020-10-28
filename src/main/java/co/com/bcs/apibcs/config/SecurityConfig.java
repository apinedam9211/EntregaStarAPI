package co.com.bcs.apibcs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;


@EnableWebSecurity
@Configuration
@Profile("auth")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${oauth2.instrospector.url}")
	private String urlIntrospector;

	@Value("${oauth2.instrospector.user}")
	private String userOauth;

	@Value("${oauth2.instrospector.pass}")
	private String passOauth;

	@Value("${apibcs.role.access}")
	private String rol;

	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/starAPI/**").hasAuthority(rol).and().oauth2ResourceServer()
				.opaqueToken(x -> x.introspector(instrospectorCustom())).and().csrf().disable();
	}

	@Bean
	public OpaqueTokenIntrospector instrospectorCustom() {
		return new CustomOpaqueInstrospector(urlIntrospector, userOauth, passOauth);

	}

}