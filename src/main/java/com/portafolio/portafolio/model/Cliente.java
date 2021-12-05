package com.portafolio.portafolio.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity
@Table(name="CLIENTE")
public class Cliente {

	@Id
	private int id_cliente;
	private String rut_cliente;
	private String nombre_cliente;
	
	public Cliente() {
	
	}

	public Cliente(int id_cliente, String rut_cliente, String nombre_cliente) {
		this.id_cliente = id_cliente;
		this.rut_cliente = rut_cliente;
		this.nombre_cliente = nombre_cliente;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getRut_cliente() {
		return rut_cliente;
	}

	public void setRut_cliente(String rut_cliente) {
		this.rut_cliente = rut_cliente;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	
	
	
	
	
	
	
	
	
	
	 
	 
	
	
}
