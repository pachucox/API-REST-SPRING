package com.portafolio.portafolio.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.portafolio.portafolio.model.Tarea;

@Repository
public interface TareaRepo {
	

	 public Tarea agregarTarea(Tarea tarea);
	 public Tarea actualizarTarea(Tarea tarea);
	 public List<Tarea> allTareas();
	//  public String obtenerFlujo(int id);
	 public void eliminarTarea(int id);
	 public List<Tarea> leerTareas(int id_responsable);
	 public List<Tarea> leerTareasCreador(int id_creador);

}
