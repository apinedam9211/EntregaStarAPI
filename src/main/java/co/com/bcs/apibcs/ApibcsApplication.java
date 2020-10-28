package co.com.bcs.apibcs;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ApibcsApplication {

	private static final Class<ApibcsApplication> SOURCE = ApibcsApplication.class;

	public static void main(String[] args) {
		final SpringApplicationBuilder app = new SpringApplicationBuilder(ApibcsApplication.SOURCE);
		app.profiles("tomcat");
		app.web(WebApplicationType.SERVLET);
		app.run(args);
	}

}
