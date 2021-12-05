package com.portafolio.portafolio.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portafolio.portafolio.model.Usuario;


@Repository
public interface UsuarioRepo {
	
	public Usuario agregarUsuario(Usuario usuario);
	public Usuario actualizarUsuario(Usuario usuario);
	public List<Usuario> allUsuarios();
	public void eliminarUsuario(int id);
	
	

}
