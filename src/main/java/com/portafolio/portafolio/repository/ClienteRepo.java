package com.portafolio.portafolio.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portafolio.portafolio.model.Cliente;



@Repository
public interface ClienteRepo {
	
	 public Cliente agregarCliente(Cliente cliente);
	 public Cliente actualizarCliente(Cliente cliente);
	 public List<Cliente> allClientes();
	 public String obtenerCliente(int id);
	 public void eliminarCliente(int id);

}
