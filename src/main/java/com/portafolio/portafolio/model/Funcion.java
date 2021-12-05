package com.portafolio.portafolio.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity
@Table(name="FUNCION")
public class Funcion {
	
	@Id
	private int id_funcion;
	private String nombre_funcion;
	private int flujo_id_flujo;
	
	public Funcion() {
	
	}

	public Funcion(int id_funcion, String nombre_funcion, int flujo_id_flujo) {
		this.id_funcion = id_funcion;
		this.nombre_funcion = nombre_funcion;
		this.flujo_id_flujo = flujo_id_flujo;
	}

	public int getId_funcion() {
		return id_funcion;
	}

	public void setId_funcion(int id_funcion) {
		this.id_funcion = id_funcion;
	}

	public String getNombre_funcion() {
		return nombre_funcion;
	}

	public void setNombre_funcion(String nombre_funcion) {
		this.nombre_funcion = nombre_funcion;
	}

	public int getFlujo_id_flujo() {
		return flujo_id_flujo;
	}

	public void setFlujo_id_flujo(int flujo_id_flujo) {
		this.flujo_id_flujo = flujo_id_flujo;
	}
	
	
	

}
