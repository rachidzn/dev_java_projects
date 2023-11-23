package com.yaps.petstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

import com.yaps.petstore.domain.service.ShoppingCartService;
import com.yaps.petstore.domain.service.ShoppingCartServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		String[] staticResources = { "/img/**"};

        http
        	.csrf().disable()
	        .formLogin()
	        	.loginProcessingUrl("/login")
	        	.loginPage("/login")
	        	.defaultSuccessUrl("/")
	        	.and()
	        .logout()
	        	.logoutSuccessUrl("/")
	        	.and()
	        .authorizeRequests()
	        	.antMatchers(staticResources).permitAll()
	        	.antMatchers("/", "/login","/new-account","/findProducts","/findItems","/findItem").permitAll()
	        	.anyRequest().authenticated();
	}
	
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // https://www.baeldung.com/spring-mvc-session-attributes
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ShoppingCartService shoppingCartService() {
        return new ShoppingCartServiceImpl();
    }

}
