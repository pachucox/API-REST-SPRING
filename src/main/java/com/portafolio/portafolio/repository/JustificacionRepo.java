package com.portafolio.portafolio.repository;


import java.util.List;
import org.springframework.stereotype.Repository;

import com.portafolio.portafolio.model.Justificacion;

@Repository
public interface JustificacionRepo {
	
	 public List<Justificacion> allJustificaciones();
	 public Justificacion agregarJustificacion(Justificacion justificacion);
	 public Justificacion actualizarJustificacion(Justificacion justificacion);
	 public void eliminarJustificacion(int id);
	 public String obtenerEmailCreador(int id);

}
