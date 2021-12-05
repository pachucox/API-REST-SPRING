package com.portafolio.portafolio.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
//import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.portafolio.portafolio.model.Funcion;
import com.portafolio.portafolio.repository.FuncionRepo;

@Service
public class FuncionService implements FuncionRepo {
  
  
	  @Autowired
	  private JdbcTemplate jdbcTemplate;
	  private DataSource data;

     @Override
		public List<Funcion> allFunciones() {
			List<Funcion> lista = null;
		    SimpleJdbcCall sm;
		        jdbcTemplate.setResultsMapCaseInsensitive(true);
		        sm = new SimpleJdbcCall(jdbcTemplate)
		                        .withProcedureName("SP_LEER_FUNCIONES")
		                        .returningResultSet("OUT_FUNCIONES_CURSOR",
		                        		BeanPropertyRowMapper.newInstance(Funcion.class));

		     Map m = sm.execute(new HashMap<String, Object>(0));
		     return (List) m.get("OUT_FUNCIONES_CURSOR");
		}
	  
	 
	  
	  @Override
	  public Funcion agregarFuncion(Funcion funcion) {
		  Funcion f = new Funcion();
		  
		  f.setId_funcion(funcion.getId_funcion());
		  f.setNombre_funcion(funcion.getNombre_funcion());
		  f.setFlujo_id_flujo(funcion.getFlujo_id_flujo());
		  
		  jdbcTemplate.update("call SP_AGREGA_FUNCION(?,?)",
					f.getNombre_funcion(),
					f.getFlujo_id_flujo());
		  
		  try {
				int id = 0;
				String sql = "SELECT MAX(id_funcion) as id_funcion FROM FUNCION";
				data = jdbcTemplate.getDataSource();
				PreparedStatement p = data.getConnection().prepareStatement(sql);
		        ResultSet rs = p.executeQuery();
		        while(rs.next()) {
		        	id = rs.getInt(1);
		        	f.setId_funcion(id);
		        }
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		  return f;
	  }
	  
	  @Override
		public Funcion actualizarFuncion(Funcion funcion) {
			Funcion f = new Funcion();
			
		  f.setId_funcion(funcion.getId_funcion());
		  f.setNombre_funcion(funcion.getNombre_funcion());
		  f.setFlujo_id_flujo(funcion.getFlujo_id_flujo());
			
			try (Connection con = jdbcTemplate.getDataSource().getConnection();){
				
				jdbcTemplate.update("call SP_ACTUALIZA_FUNCION(?,?,?)"
					  ,f.getId_funcion()
				    ,f.getNombre_funcion()
				    ,f.getFlujo_id_flujo());
			}catch (Exception e) {
		            e.printStackTrace();
		        }
			return f;
		}
	  
	  @Override
	  public void eliminarFuncion(int id) {
			jdbcTemplate.update("call SP_ELIMINA_FUNCION(?)",id);
		}
}
