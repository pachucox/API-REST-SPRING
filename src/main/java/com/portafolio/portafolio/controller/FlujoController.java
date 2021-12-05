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

import com.portafolio.portafolio.model.Flujo;

import com.portafolio.portafolio.service.FlujoService;

@RestController
@RequestMapping("/flujo")
public class FlujoController {
  
    @Autowired 
	  private FlujoService servicio;
	 
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @RequestMapping(method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	  public Flujo agregarFlujo(@RequestBody Flujo flujo) {
		  return servicio.agregarFlujo(flujo);
	  }

	  @CrossOrigin(origins =  "http://localhost:3000")
	  @GetMapping("/all")
	  public List<Flujo> allFlujos() {
	    return servicio.allFlujos();
	  }

	  
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
		public Flujo actualizarFlujo(@RequestBody Flujo flujo) {
			return servicio.actualizarFlujo(flujo);
		}
	  
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @DeleteMapping("/{id}")
	  	public void  eliminarFlujo(@PathVariable(name = "id")int id) {
			servicio.eliminarFlujo(id);
		}

}
