package com.portafolio.portafolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.portafolio.portafolio.model.Usuario;
import com.portafolio.portafolio.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService servicio;
	
	@CrossOrigin(origins =  "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	public Usuario agregarUsuario(@RequestBody Usuario usuario) {
		return servicio.agregarUsuario(usuario);
	}
	
	@CrossOrigin(origins =  "http://localhost:3000")
	@GetMapping("/all")
	   public List<Usuario> allUsuarios(){
	    	return servicio.allUsuarios();
	}
	
	@CrossOrigin(origins =  "http://localhost:3000")
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public Usuario actualizarUsuario(@RequestBody Usuario usuario) {
		return servicio.actualizarUsuario(usuario);
	}
	
	@CrossOrigin(origins =  "http://localhost:3000")
	@DeleteMapping("/{id}")
	public void  eliminarUsuario(@PathVariable(name = "id")int id) {
		servicio.eliminarUsuario(id);
	}
	
}
