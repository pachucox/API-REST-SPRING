package com.portafolio.portafolio.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portafolio.portafolio.model.Flujo;

@Repository
public interface FlujoRepo {

  
	 public Flujo agregarFlujo(Flujo flujo);
	 public Flujo actualizarFlujo(Flujo flujo);
	 public List<Flujo> allFlujos();
	//  public String obtenerFlujo(int id);
	 public void eliminarFlujo(int id);
  
}
