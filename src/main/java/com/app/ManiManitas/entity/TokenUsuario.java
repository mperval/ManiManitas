package com.app.ManiManitas.entity;

public class TokenUsuario {

	private String token;
	private String tipo = "Bearer ";

	public TokenUsuario(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TokenUsuario [token=" + token + ", tipo=" + tipo + "]";
	}
}
