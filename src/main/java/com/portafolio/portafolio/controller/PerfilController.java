package com.portafolio.portafolio.controller;

import java.util.List;


import com.portafolio.portafolio.model.Perfil;
import com.portafolio.portafolio.service.PerfilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

  @Autowired PerfilService servicio;

  @CrossOrigin(origins =  "http://localhost:3000")
  @GetMapping("/all")
  public List<Perfil> allPerfiles() {
    return servicio.allPerfiles();
  }

  @CrossOrigin(origins =  "http://localhost:3000")
  @GetMapping("/usuario/{id}")
  public String obtenerPerfil(@PathVariable("id") int id){
    return servicio.obtenerPerfil(id);
  }
  

  @CrossOrigin(origins =  "http://localhost:3000")
  @RequestMapping(method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
  public Perfil agregarPerfil(@RequestBody Perfil perfil) {
	  return servicio.agregarPerfil(perfil);
  }
  
  @CrossOrigin(origins =  "http://localhost:3000")
  @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public Perfil actualizarPerfil(@RequestBody Perfil perfil) {
		return servicio.actualizarPerfil(perfil);
	}
  
  @CrossOrigin(origins =  "http://localhost:3000")
  @DeleteMapping("/{id}")
  	public void  eliminarPerfil(@PathVariable(name = "id")int id) {
		servicio.eliminarPerfil(id);
	}

  
}
