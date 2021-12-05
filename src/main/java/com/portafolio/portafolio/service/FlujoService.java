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


import com.portafolio.portafolio.model.Flujo;
import com.portafolio.portafolio.repository.FlujoRepo;

@Service
public class FlujoService implements FlujoRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private DataSource data;

	@Override
	public List<Flujo> allFlujos() {
		List<Flujo> lista = null;
		SimpleJdbcCall sm;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		sm = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_LEER_FLUJOS").returningResultSet("OUT_FLUJOS_CURSOR",
				BeanPropertyRowMapper.newInstance(Flujo.class));

		Map m = sm.execute(new HashMap<String, Object>(0));
		return (List) m.get("OUT_FLUJOS_CURSOR");
	}

	@Override
	public Flujo agregarFlujo(Flujo flujo) {
		Flujo f = new Flujo();

		f.setId_flujo(flujo.getId_flujo());
		f.setDescripcion_flujo(flujo.getDescripcion_flujo());
		f.setFecha_inicio(flujo.getFecha_inicio());
		f.setFecha_termino(flujo.getFecha_termino());
		f.setTipo_flujo(flujo.getTipo_flujo());
		f.setDepartamento_id_departamento(flujo.getDepartamento_id_departamento());

		jdbcTemplate.update("call SP_AGREGA_FLUJO(?,?,?,?,?)", f.getDescripcion_flujo(), f.getFecha_inicio(),
				f.getFecha_termino(), f.getTipo_flujo(), f.getDepartamento_id_departamento());

		try {
			int id = 0;
			String sql = "SELECT MAX(id_flujo) as id_flujo FROM FLUJO";
			data = jdbcTemplate.getDataSource();
			PreparedStatement p = data.getConnection().prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
				f.setId_flujo(id);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return f;
	}

	@Override
	public Flujo actualizarFlujo(Flujo flujo) {
		Flujo f = new Flujo();

		f.setId_flujo(flujo.getId_flujo());
		f.setDescripcion_flujo(flujo.getDescripcion_flujo());
		f.setFecha_inicio(flujo.getFecha_inicio());
		f.setFecha_termino(flujo.getFecha_termino());
		f.setTipo_flujo(flujo.getTipo_flujo());
		f.setDepartamento_id_departamento(flujo.getDepartamento_id_departamento());

		try (Connection con = jdbcTemplate.getDataSource().getConnection();) {

			jdbcTemplate.update("call SP_ACTUALIZA_FLUJO(?,?,?,?,?,?)", f.getId_flujo(), f.getDescripcion_flujo(),
					f.getFecha_inicio(), f.getFecha_termino(), f.getTipo_flujo(), f.getDepartamento_id_departamento());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public void eliminarFlujo(int id) {
		jdbcTemplate.update("call SP_ELIMINA_FLUJO(?)", id);
	}

}
