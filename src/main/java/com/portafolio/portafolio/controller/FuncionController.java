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

import com.portafolio.portafolio.model.Funcion;

import com.portafolio.portafolio.service.FuncionService;

@RestController
@RequestMapping("/funcion")
public class FuncionController {
  

	  @Autowired 
	  private FuncionService servicio;
	 
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @RequestMapping(method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	  public Funcion agregarFuncion(@RequestBody Funcion funcion) {
		  return servicio.agregarFuncion(funcion);
	  }

	  @CrossOrigin(origins =  "http://localhost:3000")
	  @GetMapping("/all")
	  public List<Funcion> allFunciones() {
	    return servicio.allFunciones();
	  }

	
	  
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
		public Funcion actualizarFuncion(@RequestBody Funcion funcion) {
			return servicio.actualizarFuncion(funcion);
		}
	  
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @DeleteMapping("/{id}")
	  	public void  eliminarFuncion(@PathVariable(name = "id")int id) {
			servicio.eliminarFuncion(id);
		}

}
