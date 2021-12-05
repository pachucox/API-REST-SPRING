package com.portafolio.portafolio.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portafolio.portafolio.model.Funcion;

@Repository
public interface FuncionRepo {

  public Funcion agregarFuncion(Funcion funcion);
	public Funcion actualizarFuncion(Funcion funcion);
	public List<Funcion> allFunciones();
	//  public String obtenerFuncion(int id);
	public void eliminarFuncion(int id);
  
}
