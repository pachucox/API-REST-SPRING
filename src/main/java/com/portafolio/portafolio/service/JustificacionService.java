package com.portafolio.portafolio.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.portafolio.portafolio.model.Justificacion;
import com.portafolio.portafolio.repository.JustificacionRepo;

@Service
public class JustificacionService implements JustificacionRepo {
	

	  @Autowired
	  private JdbcTemplate jdbcTemplate;
	  private DataSource data;
	  private SimpleJdbcCall simpleJdbcCall;
	  
	  @Override
		public List<Justificacion> allJustificaciones() {
			List<Justificacion> lista = null;
		    SimpleJdbcCall sm;
		        jdbcTemplate.setResultsMapCaseInsensitive(true);
		        sm = new SimpleJdbcCall(jdbcTemplate)
		                        .withProcedureName("SP_LEER_JUSTIFICACIONES")
		                        .returningResultSet("OUT_JUSTIFICACIONES_CURSOR",
		                        		BeanPropertyRowMapper.newInstance(Justificacion.class));

		     Map m = sm.execute(new HashMap<String, Object>(0));
		     return (List) m.get("OUT_JUSTIFICACIONES_CURSOR");
		}
	  
	  
	  @Override
	  public Justificacion agregarJustificacion(Justificacion justi) {
		  Justificacion j = new Justificacion();
		  
		  j.setId_justificacion(justi.getId_justificacion());
		  j.setDescripcion_justificacion(justi.getDescripcion_justificacion());
		  j.setTarea_id_tarea(justi.getTarea_id_tarea());
		  
		  jdbcTemplate.update("call SP_AGREGA_JUSTIFICACION(?,?)",
					j.getDescripcion_justificacion(),
					j.getTarea_id_tarea());
		  
		  try {
				int id = 0;
				String sql = "SELECT MAX(id_justificacion) as id_justificacion FROM JUSTIFICACION";
				data = jdbcTemplate.getDataSource();
				PreparedStatement p = data.getConnection().prepareStatement(sql);
		        ResultSet rs = p.executeQuery();
		        while(rs.next()) {
		        	id = rs.getInt(1);
		        	j.setId_justificacion(id);
		        }
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		  return j;
	  }
	  
	  @Override
		public Justificacion actualizarJustificacion(Justificacion justi) {
		  Justificacion j = new Justificacion();
			
		  j.setId_justificacion(justi.getId_justificacion());
		  j.setDescripcion_justificacion(justi.getDescripcion_justificacion());
		  j.setTarea_id_tarea(justi.getTarea_id_tarea());
			
			try (Connection con = jdbcTemplate.getDataSource().getConnection();){
				
				jdbcTemplate.update("call SP_ACTUALIZA_JUSTIFICACION(?,?,?)"
					,j.getId_justificacion()
				    ,j.getDescripcion_justificacion()
				    ,j.getTarea_id_tarea());
			}catch (Exception e) {
		            e.printStackTrace();
		        }
			return j;
		}
	  
	  @Override
	  public void eliminarJustificacion(int id) {
			jdbcTemplate.update("call SP_ELIMINA_JUSTIFICACION(?)",id);
		}
	  
		@Override
		public String obtenerEmailCreador(int id) {

			String email = "";
			String query = "SELECT U.EMAIL_USUARIO as EMAIL FROM USUARIO U JOIN TAREA T ON T.ID_CREADOR= U.ID_USUARIO WHERE T.ID_TAREA = "
					+ id + "";
			try (Connection con = jdbcTemplate.getDataSource().getConnection();) {
				PreparedStatement p = con.prepareStatement(query);
				ResultSet rs = p.executeQuery();
				while (rs.next()) {
					email = rs.getString("EMAIL");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return email;
		}

}
