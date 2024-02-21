package com.app.ManiManitas.security;


import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTGenerator {

	private static final long JWT_EXPIRTATION_TIME = 3000000;
	private static final String JWT_FIRMA = "Mr.Spring";
	
	public static String generatorToken(Authentication authentication) {
		
		String username =  authentication.getName();
		Date tiempoActual = new Date();
		Date expiracionToken = new Date(tiempoActual.getTime() + JWT_EXPIRTATION_TIME);
		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(tiempoActual)
				.setExpiration(expiracionToken)
				.signWith(SignatureAlgorithm.HS512, JWT_FIRMA)
				.compact();
		
		return token;
	}
	
	public String obtenerUsernameJWT(String token) {
		return Jwts.parser().setSigningKey(JWT_FIRMA).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(JWT_FIRMA).parseClaimsJws(token);
			return true;
			
		}catch(Exception e) {
			throw new AuthenticationCredentialsNotFoundException("JWT ha expirado o no es valido");
		}
	}
}
