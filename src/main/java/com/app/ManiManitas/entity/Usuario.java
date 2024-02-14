package com.app.ManiManitas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Usuario extends DomainEntity{
	
	private static final long serialVersionUID = 1L;
	@Column(unique=true)
	private String username;
	private String password;
	private String urlPhoto;
	private String email;
	private String address;
	private Rol rol;
	
	
	public Usuario(int id, int version) {
		super(id, version);
	}


	public Usuario() {
	}


	public Usuario(int id, int version, String username, String password, String urlPhoto, String email, String address, Rol rol) {
		super(id, version);
		this.username = username;
		this.password = password;
		this.urlPhoto = urlPhoto;
		this.email = email;
		this.address = address;
		this.rol = rol;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUrlPhoto() {
		return urlPhoto;
	}


	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Rol getRol() {
		return rol;
	}


	public void setRol(Rol rol) {
		this.rol = rol;
	}


	@Override
	public String toString() {
		return "Usuario [username=" + username + ", password=" + password + ", urlPhoto=" + urlPhoto + ", email="
				+ email + ", address=" + address + ", rol=" + rol + "]";
	}


}
