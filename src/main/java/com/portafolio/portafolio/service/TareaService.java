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


import com.portafolio.portafolio.model.Tarea;
import com.portafolio.portafolio.repository.TareaRepo;

@Service
public class TareaService implements TareaRepo {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;
	private DataSource data;
	

	@Override
	public List<Tarea> allTareas() {
		List<Tarea> lista = null;
		SimpleJdbcCall sm;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		sm = new SimpleJdbcCall(jdbcTemplate).
				withProcedureName("SP_LEER_TAREAS").
				returningResultSet("OUT_TAREAS_CURSOR",BeanPropertyRowMapper.newInstance(Tarea.class));
		Map m = sm.execute(new HashMap<String, Object>(0));
		return (List) m.get("OUT_TAREAS_CURSOR");
	}
	
	
	
	@Override
	public List<Tarea> leerTareas(int id_responsable) {
		List<Tarea> tarea2= null;
        try {

        	data = jdbcTemplate.getDataSource();
            simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).
                    withProcedureName("SP_TAREAS_USUARIO").
                    returningResultSet("OUT_TAREA_CURSOR", BeanPropertyRowMapper.newInstance(Tarea.class));
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("IN_ID_RESPONSABLE", id_responsable);
            Map<String, Object> out_Values = simpleJdbcCall.execute(params);
            @SuppressWarnings("unchecked")
			ArrayList<Tarea> list = (ArrayList<Tarea>) out_Values.get("OUT_TAREA_CURSOR");
            tarea2 = list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tarea2;

	}
	
	@Override
	public List<Tarea> leerTareasCreador(int id_creador) {
		List<Tarea> tarea2= null;
        try {

        	data = jdbcTemplate.getDataSource();
            simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).
                    withProcedureName("SP_TAREAS_USUARIO_CREADOR").
                    returningResultSet("OUT_TAREA_CURSOR", BeanPropertyRowMapper.newInstance(Tarea.class));
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("IN_ID_CREADOR", id_creador);
            Map<String, Object> out_Values = simpleJdbcCall.execute(params);
            @SuppressWarnings("unchecked")
			ArrayList<Tarea> list = (ArrayList<Tarea>) out_Values.get("OUT_TAREA_CURSOR");
            tarea2 = list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tarea2;

	}
	
	

	@Override
	public Tarea agregarTarea(Tarea tarea) {
		Tarea t = new Tarea();

		t.setId_tarea(tarea.getId_tarea());
		t.setDescripcion_tarea(tarea.getDescripcion_tarea());
		t.setId_responsable(tarea.getId_responsable());
		t.setId_creador(tarea.getId_creador());
		t.setFecha_inicio(tarea.getFecha_inicio());
		t.setFecha_termino(tarea.getFecha_termino());
		t.setFecha_limite(tarea.getFecha_limite());
		t.setId_tareapadre(tarea.getId_tareapadre());
		t.setTarea_id_tarea(tarea.getTarea_id_tarea());
		t.setFuncion_id_funcion(tarea.getFuncion_id_funcion());

		jdbcTemplate.update("call SP_AGREGA_TAREA(?,?,?,?,?,?,?,?,?)", t.getDescripcion_tarea(), t.getId_responsable(),
				t.getId_creador(), t.getFecha_inicio(), t.getFecha_termino(),t.getFecha_limite(),
				t.getId_tareapadre(),t.getTarea_id_tarea(),t.getFuncion_id_funcion());

		try {
			int id = 0;
			String sql = "SELECT MAX(id_tarea) as id_tarea FROM TAREA";
			data = jdbcTemplate.getDataSource();
			PreparedStatement p = data.getConnection().prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
				t.setId_tarea(id);
			}
			jdbcTemplate.update("call SP_TAREA_USUARIO(?,?)",t.getId_tarea(),t.getId_responsable());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return t;
	}

	@Override
	public Tarea actualizarTarea(Tarea tarea) {
		Tarea t = new Tarea();

		t.setId_tarea(tarea.getId_tarea());
		t.setDescripcion_tarea(tarea.getDescripcion_tarea());
		t.setId_responsable(tarea.getId_responsable());
		t.setId_creador(tarea.getId_creador());
		t.setFecha_inicio(tarea.getFecha_inicio());
		t.setFecha_termino(tarea.getFecha_termino());
		t.setFecha_limite(tarea.getFecha_limite());
		t.setId_tareapadre(tarea.getId_tareapadre());
		t.setTarea_id_tarea(tarea.getTarea_id_tarea());
		t.setFuncion_id_funcion(tarea.getFuncion_id_funcion());

		try (Connection con = jdbcTemplate.getDataSource().getConnection();) {

			jdbcTemplate.update("call SP_ACTUALIZA_TAREA(?,?,?,?,?,?,?,?,?,?)", t.getId_tarea(),t.getDescripcion_tarea(), t.getId_responsable(),
					t.getId_creador(), t.getFecha_inicio(), t.getFecha_termino(),t.getFecha_limite(),
					t.getId_tareapadre(),t.getTarea_id_tarea(),t.getFuncion_id_funcion());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public void eliminarTarea(int id) {
		jdbcTemplate.update("call SP_ELIMINA_TAREA(?)", id);
	}

}
