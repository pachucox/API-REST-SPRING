package com.portafolio.portafolio.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity
@Table(name="DEPARTAMENTO")
public class Departamento {
	
	@Id
	private int id_departamento;
	private String nombre_departamento;
	private int cliente_id_cliente;
	
	public Departamento() {
		
	}
	
	public Departamento(int id_departamento, String nombre_departamento, int cliente_id_cliente) {
		this.id_departamento = id_departamento;
		this.nombre_departamento = nombre_departamento;
		this.cliente_id_cliente = cliente_id_cliente;
	}

	public int getId_departamento() {
		return id_departamento;
	}

	public void setId_departamento(int id_departamento) {
		this.id_departamento = id_departamento;
	}

	public String getNombre_departamento() {
		return nombre_departamento;
	}

	public void setNombre_departamento(String nombre_departamento) {
		this.nombre_departamento = nombre_departamento;
	}

	public int getCliente_id_cliente() {
		return cliente_id_cliente;
	}

	public void setCliente_id_cliente(int cliente_id_cliente) {
		this.cliente_id_cliente = cliente_id_cliente;
	}
	
	
	
	
	
}
