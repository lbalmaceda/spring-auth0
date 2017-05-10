package com.github.fromi.openidconnect.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import javax.annotation.Resource;

import static java.util.Arrays.asList;

@Configuration
@EnableOAuth2Client
public class OAuth2Client {

    @Value("${auth0.oauth2.scopes}")
    private String scopes;

    @Value("${auth0.oauth2.domain}")
    private String domain;

    @Value("${auth0.oauth2.clientId}")
    private String clientId;

    @Value("${auth0.oauth2.clientSecret}")
    private String clientSecret;

    @Bean
    // TODO retrieve from https://domain.auth0.com/.well-known/openid-configuration ?
    public OAuth2ProtectedResourceDetails auth0OAuth2Details() {
        AuthorizationCodeResourceDetails oAuth2Details = new AuthorizationCodeResourceDetails();
        oAuth2Details.setAuthenticationScheme(AuthenticationScheme.form);
        oAuth2Details.setClientAuthenticationScheme(AuthenticationScheme.form);
        oAuth2Details.setClientId(clientId);
        oAuth2Details.setClientSecret(clientSecret);
        oAuth2Details.setUserAuthorizationUri(domain + "authorize");
        oAuth2Details.setAccessTokenUri(domain + "oauth/token");
        oAuth2Details.setScope(asList(scopes));
        return oAuth2Details;
    }

    @SuppressWarnings("SpringJavaAutowiringInspection") // Provided by Spring Boot
    @Resource
    private OAuth2ClientContext oAuth2ClientContext;

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
    public OAuth2RestOperations auth0OAuth2RestTemplate() {
        return new OAuth2RestTemplate(auth0OAuth2Details(), oAuth2ClientContext);
    }
}
