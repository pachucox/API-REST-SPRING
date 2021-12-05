package com.portafolio.portafolio.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


import com.portafolio.portafolio.model.Departamento;
import com.portafolio.portafolio.repository.DepartamentoRepo;


@Service
public class DepartamentoService implements DepartamentoRepo {


	  @Autowired
	  private JdbcTemplate jdbcTemplate;
	  private DataSource data;
	  private SimpleJdbcCall simpleJdbcCall;
	  
	  @Override
		public List<Departamento> allDeptos() {
			List<Departamento> lista = null;
		    SimpleJdbcCall sm;
		        jdbcTemplate.setResultsMapCaseInsensitive(true);
		        sm = new SimpleJdbcCall(jdbcTemplate)
		                        .withProcedureName("SP_LEER_DEPTOS")
		                        .returningResultSet("OUT_DEPTOS_CURSOR",
		                        		BeanPropertyRowMapper.newInstance(Departamento.class));

		     Map m = sm.execute(new HashMap<String, Object>(0));
		     return (List) m.get("OUT_DEPTOS_CURSOR");
		}
	  
	  
	  @Override
	  public Departamento agregarDepto(Departamento depto) {
		  Departamento d = new Departamento();
		  
		  d.setId_departamento(depto.getId_departamento());
		  d.setNombre_departamento(depto.getNombre_departamento());
		  d.setCliente_id_cliente(depto.getCliente_id_cliente());
		  
		  jdbcTemplate.update("call SP_AGREGA_DEPTO(?,?)",
					d.getNombre_departamento(),
					d.getCliente_id_cliente());
		  
		  try {
				int id = 0;
				String sql = "SELECT MAX(id_departamento) as id_departamento FROM DEPARTAMENTO";
				data = jdbcTemplate.getDataSource();
				PreparedStatement p = data.getConnection().prepareStatement(sql);
		        ResultSet rs = p.executeQuery();
		        while(rs.next()) {
		        	id = rs.getInt(1);
		        	d.setId_departamento(id);
		        }
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		  return d;
	  }
	  
	  @Override
		public Departamento actualizarDepto(Departamento depto) {
			Departamento d = new Departamento();
			
			d.setId_departamento(depto.getId_departamento());
			d.setNombre_departamento(depto.getNombre_departamento());
			d.setCliente_id_cliente(depto.getCliente_id_cliente());
			
			try (Connection con = jdbcTemplate.getDataSource().getConnection();){
				
				jdbcTemplate.update("call SP_ACTUALIZA_DEPTO(?,?,?)"
					,d.getId_departamento()
				    ,d.getNombre_departamento()
				    ,d.getCliente_id_cliente());
			}catch (Exception e) {
		            e.printStackTrace();
		        }
			return d;
		}
	  
	  @Override
	  public void eliminarDepto(int id) {
			jdbcTemplate.update("call SP_ELIMINA_DEPTO(?)",id);
		}
	  
	 
	
}
