package com.portafolio.portafolio.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PERFIL")
public class Perfil {

	@Id
	private int id_perfil;
	private String nombre_perfil;
	
	public Perfil() {
	
	}

	public Perfil(int id_perfil, String nombre_perfil) {
		this.id_perfil = id_perfil;
		this.nombre_perfil = nombre_perfil;
	}

	public int getId_perfil() {
		return id_perfil;
	}

	public void setId_perfil(int id_perfil) {
		this.id_perfil = id_perfil;
	}

	public String getNombre_perfil() {
		return nombre_perfil;
	}

	public void setNombre_perfil(String nombre_perfil) {
		this.nombre_perfil = nombre_perfil;
	}
	
	
	
}
