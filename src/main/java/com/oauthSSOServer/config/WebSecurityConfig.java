package com.oauthSSOServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.oauthSSOServer.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity(debug=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private MyUserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//super.configure(auth);
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		//super.configure(web);
		web.ignoring().antMatchers("/assets/**", "/css/**", "/images/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		http.authorizeRequests()
        	.antMatchers("/login","/app/api/**","/captcha.jpg","/oauth/token","/oauth/token_key").permitAll()
        	.anyRequest()
        	.authenticated()
        	.and()
        		.csrf().disable().cors()
        	.and()
        	.formLogin()
			.loginPage("/mylogin/page")
			.loginProcessingUrl("/auth/form")
			.permitAll()
			.defaultSuccessUrl("http://127.0.0.1:8081/member/");
	}
	
}
