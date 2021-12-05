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


import com.portafolio.portafolio.model.Departamento;
import com.portafolio.portafolio.service.DepartamentoService;




@RestController
@RequestMapping("/depto")
public class DepartamentoController {

	  @Autowired DepartamentoService servicio;
	  
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @GetMapping("/all")
	  public List<Departamento> allDeptos() {
	    return servicio.allDeptos();
	  }
	  
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @RequestMapping(method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	  public Departamento agregarDepto(@RequestBody Departamento depto) {
		  return servicio.agregarDepto(depto);
	  }
	  
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
		public Departamento actualizarDepto(@RequestBody Departamento depto) {
			return servicio.actualizarDepto(depto);
		}
	  
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @DeleteMapping("/{id}")
	  	public void  eliminarDepto(@PathVariable(name = "id")int id) {
			servicio.eliminarDepto(id);
		}
	
}
