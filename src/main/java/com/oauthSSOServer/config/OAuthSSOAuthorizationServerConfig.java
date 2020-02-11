package com.oauthSSOServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuthSSOAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		//super.configure(security);
		security.tokenKeyAccess("permitAll()")
			.checkTokenAccess("permitAll()")
			.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//super.configure(clients);
		clients.inMemory()
			.withClient("client-member")
			.secret(passwordEncoder.encode("123456"))
			.authorizedGrantTypes("authorization_code","refresh_token")
			.accessTokenValiditySeconds(7200)
			.refreshTokenValiditySeconds(72000)
			//.redirectUris("http://www.baidu.com")
			.redirectUris("http://127.0.0.1:8081/member/login","http://localhost:8081/member/login")
			//.additionalInformation()
			//.resourceIds(ResourceServerConfig.Re)
			.scopes("all")
			.autoApprove(true)
			.and()
			.withClient("client-order")
			.secret(passwordEncoder.encode("654321"))
			.authorizedGrantTypes("authorization_code","refresh_token")
			.accessTokenValiditySeconds(7200)
			.refreshTokenValiditySeconds(72000)
			//.redirectUris("http://www.baidu.com")
			.redirectUris("http://127.0.0.1:8082/order/login","http://localhost:8082/order/login")
			//.additionalInformation()
			//.resourceIds(ResourceServerConfig.Re)
			.scopes("all")
			.autoApprove(true);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		//super.configure(endpoints);
		endpoints.accessTokenConverter(jwtAccessTokenConverter());
        endpoints.tokenStore(jwtTokenStore());
	}
	

	@Bean
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("cjs");   //  Sets the JWT signing key
        return jwtAccessTokenConverter;
    }

}
