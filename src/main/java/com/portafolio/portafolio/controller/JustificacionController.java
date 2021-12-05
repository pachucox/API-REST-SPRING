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

import com.portafolio.portafolio.model.Justificacion;
import com.portafolio.portafolio.service.JustificacionService;


@RestController
@RequestMapping("/justificacion")
public class JustificacionController {
	
	  @Autowired JustificacionService servicio;
	  
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @GetMapping("/all")
	  public List<Justificacion> allJustificaciones() {
	    return servicio.allJustificaciones();
	  }
	  
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @RequestMapping(method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	  public Justificacion agregarJustificacion(@RequestBody Justificacion justi) {
		  return servicio.agregarJustificacion(justi);
	  }
	  
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
		public Justificacion actualizarJustificacion(@RequestBody Justificacion justi) {
			return servicio.actualizarJustificacion(justi);
		}
	  
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @DeleteMapping("/{id}")
	  	public void  eliminarJustificacion(@PathVariable(name = "id")int id) {
			servicio.eliminarJustificacion(id);
		}
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @GetMapping("/creador/{id}")
	  public String obtenerEmailCreador(@PathVariable("id") int id){
	    return servicio.obtenerEmailCreador(id);
	  }
	

}
