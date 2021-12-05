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

import com.portafolio.portafolio.model.Cliente;
import com.portafolio.portafolio.model.Departamento;
import com.portafolio.portafolio.model.Perfil;
import com.portafolio.portafolio.repository.PerfilRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class PerfilService implements PerfilRepo {
   
  @Autowired
  private JdbcTemplate jdbcTemplate;
  private Connection con;
  private DataSource data;
  
  	@Override
		public List<Perfil> allPerfiles() {
			List<Perfil> lista = null;
		    SimpleJdbcCall sm;
		        jdbcTemplate.setResultsMapCaseInsensitive(true);
		        sm = new SimpleJdbcCall(jdbcTemplate)
		                        .withProcedureName("SP_LEER_PERFILES")
		                        .returningResultSet("OUT_PERFILES_CURSOR",
		                        		BeanPropertyRowMapper.newInstance(Perfil.class));

		     Map m = sm.execute(new HashMap<String, Object>(0));
		     return (List) m.get("OUT_PERFILES_CURSOR");
		}
	  

  @Override
	public String obtenerPerfil(int id) {
		String perfil = "";
		String query = "SELECT P.NOMBRE_PERFIL as PERFIL FROM USUARIO U JOIN PERFIL P ON P.ID_PERFIL = U.PERFIL_ID_PERFIL WHERE U.ID_USUARIO = "+id+"";
		try (Connection con = jdbcTemplate.getDataSource().getConnection();){
			PreparedStatement p = con.prepareStatement(query);
	        ResultSet rs = p.executeQuery();
	        while(rs.next()) {
	        	perfil = rs.getString("PERFIL");
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return perfil;
	}	
  
  @Override
  public Perfil agregarPerfil(Perfil perfil) {
	  Perfil pe = new Perfil();
	  
	  pe.setId_perfil(perfil.getId_perfil());
	  pe.setNombre_perfil(perfil.getNombre_perfil());
	  
	  
	  jdbcTemplate.update("call SP_AGREGA_PERFIL(?)",
				pe.getNombre_perfil());
	  try {
			int id = 0;
			String sql = "SELECT MAX(id_perfil) as id_perfil FROM PERFIL";
			data = jdbcTemplate.getDataSource();
			PreparedStatement p = data.getConnection().prepareStatement(sql);
	        ResultSet rs = p.executeQuery();
	        while(rs.next()) {
	        	id = rs.getInt(1);
	        	pe.setId_perfil(id);
	        }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	  return pe;
  }
  
  	@Override
	public Perfil actualizarPerfil(Perfil perfil) {
		Perfil pe = new Perfil();
		
		pe.setId_perfil(perfil.getId_perfil());
		pe.setNombre_perfil(perfil.getNombre_perfil());
		
		try (Connection con = jdbcTemplate.getDataSource().getConnection();){
			
			jdbcTemplate.update("call SP_ACTUALIZA_PERFIL(?,?)"
				,pe.getId_perfil()
				,pe.getNombre_perfil());
		}catch (Exception e) {
	            e.printStackTrace();
	        }
		return pe;
	}
  
  @Override
  public void eliminarPerfil(int id) {
		jdbcTemplate.update("call SP_ELIMINA_PERFIL(?)",id);
	}



}
