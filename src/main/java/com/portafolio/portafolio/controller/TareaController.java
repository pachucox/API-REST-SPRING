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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.portafolio.portafolio.model.Tarea;

import com.portafolio.portafolio.service.TareaService;

@RestController
@RequestMapping("/tarea")
public class TareaController {
	
	@Autowired
	private TareaService servicio;

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Tarea agregarTarea(@RequestBody Tarea tarea) {
		return servicio.agregarTarea(tarea);
	}

	

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/all")
	public List<Tarea> allTareas() {
		return servicio.allTareas();
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/usuario")
	public List<Tarea> leerTareas(@RequestParam int id_responsable ) {
		return servicio.leerTareas(id_responsable);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/creadas")
	public List<Tarea> leerTareasCreador(@RequestParam int id_creador) {
		return servicio.leerTareasCreador(id_creador);
	}

	
	

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public Tarea actualizarTarea(@RequestBody Tarea tarea) {
		return servicio.actualizarTarea(tarea);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/{id}")
	public void eliminarTarea(@PathVariable(name = "id") int id) {
		servicio.eliminarTarea(id);
	}

}
