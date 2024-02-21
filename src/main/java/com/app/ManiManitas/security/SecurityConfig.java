package com.app.ManiManitas.security;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private JWTAuthentication JWTAuthentication;

	//Este metodo verifica la información de los usuarios que se loguean en la API
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	//Para encriptar la contraseña del usuario
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Estable un cadena de filtros en la API, ademas determina los roles de los usuarios para acceder a ciertas url
	@Bean
	SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeHttpRequests() //Peticciones HTTP deben estar permitidas
			.requestMatchers("/usuario/**").permitAll()
			.requestMatchers(HttpMethod.POST, "/category/**").hasAnyAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
			.httpBasic();
        http.addFilterBefore(JWTAuthentication, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
