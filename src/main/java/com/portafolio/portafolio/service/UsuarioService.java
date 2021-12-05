package com.portafolio.portafolio.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


import com.portafolio.portafolio.model.Usuario;
import com.portafolio.portafolio.repository.UsuarioRepo;


@Service
public class UsuarioService implements UsuarioRepo{
		
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private DataSource data;
	private SimpleJdbcCall simpleJdbcCall;
	
	@Override
	public Usuario agregarUsuario(Usuario usuario) {
		Usuario u = new Usuario();
		
		u.setId_usuario(usuario.getId_usuario());
		u.setNombre_usuario(usuario.getNombre_usuario());
		u.setEmail_usuario(usuario.getEmail_usuario());
		u.setPass_usuario(usuario.getPass_usuario());
		u.setCliente_id_cliente(usuario.getCliente_id_cliente());
		u.setPerfil_id_perfil(usuario.getPerfil_id_perfil());
		
		jdbcTemplate.update("call SP_AGREGA_USUARIO(?,?,?,?,?)",
				u.getNombre_usuario(),
				u.getEmail_usuario(),
				u.getPass_usuario(),
				u.getPerfil_id_perfil(),
				u.getCliente_id_cliente());
		 try {
				int id = 0;
				String sql = "SELECT MAX(id_usuario) as usuario FROM USUARIO";
				data = jdbcTemplate.getDataSource();
				PreparedStatement p = data.getConnection().prepareStatement(sql);
		        ResultSet rs = p.executeQuery();
		        while(rs.next()) {
		        	id = rs.getInt(1);
		        	u.setId_usuario(id);
		        }
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		return u;
	}
	

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		Usuario u = new Usuario();
		
		u.setId_usuario(usuario.getId_usuario());
		u.setNombre_usuario(usuario.getNombre_usuario());
		u.setEmail_usuario(usuario.getEmail_usuario());
		u.setPass_usuario(usuario.getPass_usuario());
		u.setPerfil_id_perfil(usuario.getPerfil_id_perfil());
		u.setCliente_id_cliente(usuario.getCliente_id_cliente());
		try (Connection con = jdbcTemplate.getDataSource().getConnection();){
			
			jdbcTemplate.update("call SP_ACTUALIZA_USUARIO(?,?,?,?,?,?)"
				,u.getId_usuario()
			    ,u.getNombre_usuario()
			    ,u.getEmail_usuario()
			    ,u.getPass_usuario()
			    ,u.getPerfil_id_perfil()
			    ,u.getCliente_id_cliente());
		}catch (Exception e) {
	            e.printStackTrace();
	        }
		return u;
	}
	
	@Override
	public List<Usuario> allUsuarios() {
		List<Usuario> lista = null;
	    SimpleJdbcCall sm;
	        jdbcTemplate.setResultsMapCaseInsensitive(true);
	        sm = new SimpleJdbcCall(jdbcTemplate)
	        .withProcedureName("SP_LEER_USUARIOS")
	        .returningResultSet("OUT_USUARIOS_CURSOR",
	        BeanPropertyRowMapper.newInstance(Usuario.class));

	     Map m = sm.execute(new HashMap<String, Object>(0));
	     return (List) m.get("OUT_USUARIOS_CURSOR");
	}
	
	@Override
	public void eliminarUsuario(int id) {
		jdbcTemplate.update("call SP_ELIMINA_USUARIO(?)",id);
	}

}
