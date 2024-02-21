package com.app.ManiManitas.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.ManiManitas.service.UsuarioService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthentication extends OncePerRequestFilter {
	@Autowired
	private JWTGenerator JWTGenerator;

	@Autowired
	private UsuarioService usuarioService;

	public String getToken(HttpServletRequest httpRequest) {
		String tokenBearer = httpRequest.getHeader("Authorization");
		if (StringUtils.hasText(tokenBearer) && tokenBearer.startsWith("Bearer ")) {
			return tokenBearer.substring(7, tokenBearer.length());
		}
		return null;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);
		if (StringUtils.hasText(token) && JWTGenerator.validarToken(token)) {
			String username = JWTGenerator.obtenerUsernameJWT(token);
			UserDetails userDetails = usuarioService.loadUserByUsername(username);
			// TODO: Cambiar de forma sencilla
			String rolUser = userDetails.getAuthorities().iterator().next().getAuthority();

			if (rolUser.equals("USER") || rolUser.equals("ADMIN") || rolUser.equals("WORKER")) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}