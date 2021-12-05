package com.portafolio.portafolio.repository;

import java.util.List;


import com.portafolio.portafolio.model.Perfil;

import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepo {
  
  public List<Perfil> allPerfiles();
  public String obtenerPerfil(int id);
  public Perfil agregarPerfil(Perfil perfil);
  public Perfil actualizarPerfil(Perfil perfil);
  public void eliminarPerfil(int id);
  

}
