package com.app.ManiManitas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.ManiManitas.entity.Usuario;
import com.app.ManiManitas.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/registro")
	public ResponseEntity<String> registro(@RequestBody Usuario u) {
		if (usuarioService.findByUsername(u.getUsername()).isPresent()) {
			return new ResponseEntity<String>("El usuario ya existe", HttpStatus.BAD_REQUEST);
		}
		Usuario usuario = new Usuario();
		usuario.setUsername(u.getUsername());
		usuario.setPassword(passwordEncoder.encode(u.getPassword()));
		usuario.setEmail(u.getEmail());
		usuario.setUrlPhoto(u.getUrlPhoto());
		usuario.setAddress(u.getAddress());
		usuario.setRol(u.getRol());
		
		usuarioService.save(usuario);
		return new ResponseEntity<String>("Usuario creado correctamente", HttpStatus.OK);
	}

}
