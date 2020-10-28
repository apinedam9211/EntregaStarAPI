package co.com.bcs.apibcs.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionClaimNames;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

import org.springframework.security.core.GrantedAuthority;


public class CustomOpaqueInstrospector implements OpaqueTokenIntrospector {

 
    private final OpaqueTokenIntrospector delegate ;

    public CustomOpaqueInstrospector(String instrospectorURI, String client, String clientSecret) {
        
        delegate = new NimbusOpaqueTokenIntrospector(instrospectorURI, client,
        clientSecret);
    }
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        OAuth2AuthenticatedPrincipal principal = this.delegate.introspect(token);

        String name = principal.getAttribute(OAuth2IntrospectionClaimNames.CLIENT_ID);

        return new DefaultOAuth2AuthenticatedPrincipal(name, principal.getAttributes(),
                extractAuthorities(principal));
    }

    private Collection<GrantedAuthority> extractAuthorities(OAuth2AuthenticatedPrincipal principal) {
        List<String> scopes = principal.getAttribute(OAuth2IntrospectionClaimNames.SCOPE);
        return scopes.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    

}