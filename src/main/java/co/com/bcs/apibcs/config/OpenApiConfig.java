package co.com.bcs.apibcs.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.*;

@SecurityScheme(name = "security_auth", type = SecuritySchemeType.OAUTH2,
		flows = @OAuthFlows(clientCredentials  = @OAuthFlow(
				authorizationUrl = "url"
				, scopes = {
				@OAuthScope(name = "read", description = "read scope"),
				@OAuthScope(name = "write", description = "write scope") })))
public class OpenApiConfig {
    
}