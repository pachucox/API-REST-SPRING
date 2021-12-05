package com.portafolio.portafolio.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="DETALLE_TAREAS")
public class Detalle_tareas {
	
	@Id
	private int id_detalle_tarea;
	private int usuario_id_usuario;
	private int tarea_id_tarea;
	
	public Detalle_tareas() {
		// TODO Auto-generated constructor stub
	}



	public Detalle_tareas(int id_detalle_tarea, int usuario_id_usuario, int tarea_id_tarea) {
		this.id_detalle_tarea = id_detalle_tarea;
		this.usuario_id_usuario = usuario_id_usuario;
		this.tarea_id_tarea = tarea_id_tarea;
	}



	public int getId_detalle_tarea() {
		return id_detalle_tarea;
	}



	public void setId_detalle_tarea(int id_detalle_tarea) {
		this.id_detalle_tarea = id_detalle_tarea;
	}



	public int getUsuario_id_usuario() {
		return usuario_id_usuario;
	}

	public void setUsuario_id_usuario(int usuario_id_usuario) {
		this.usuario_id_usuario = usuario_id_usuario;
	}

	public int getTarea_id_tarea() {
		return tarea_id_tarea;
	}

	public void setTarea_id_tarea(int tarea_id_tarea) {
		this.tarea_id_tarea = tarea_id_tarea;
	}
	
	
	
}
