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

import com.portafolio.portafolio.model.Cliente;

import com.portafolio.portafolio.service.ClienteService;




@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	  @Autowired 
	  private ClienteService servicio;
	 
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @RequestMapping(method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	  public Cliente agregarCliente(@RequestBody Cliente cliente) {
		  return servicio.agregarCliente(cliente);
	  }

	  @CrossOrigin(origins =  "http://localhost:3000")
	  @GetMapping("/all")
	  public List<Cliente> allClientes() {
	    return servicio.allClientes();
	  }

	  @CrossOrigin(origins =  "http://localhost:3000")
	  @GetMapping("/usuario/{id}")
	  public String obtenerCliente(@PathVariable("id") int id){
	    return servicio.obtenerCliente(id);
	  }
	  
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
		public Cliente actualizarCliente(@RequestBody Cliente cliente) {
			return servicio.actualizarCliente(cliente);
		}
	  
	  @CrossOrigin(origins =  "http://localhost:3000")
	  @DeleteMapping("/{id}")
	  	public void  eliminarCliente(@PathVariable(name = "id")int id) {
			servicio.eliminarCliente(id);
		}
	  

	 

}
