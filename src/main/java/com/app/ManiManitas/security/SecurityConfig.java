package com.app.ManiManitas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	//Este metodo verifica la información de los usuarios que se loquean en la API
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	//Para incriptar la contraseña del usuario.
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//Estable una cadena de filtros en la API, ademas determina los roles de los usuarios para acceder a ciertas URL
	@Bean
	SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeHttpRequests()//peticion http deben de estar permitadas
			.requestMatchers("/usuario/**").permitAll()
			.requestMatchers(HttpMethod.POST, "/category/**").hasAnyAuthority("ADMIN")
			.and()
			.httpBasic();
		return http.build();
	}
}
