package com.portafolio.portafolio.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import org.springframework.stereotype.Service;

import com.portafolio.portafolio.model.Cliente;
import com.portafolio.portafolio.repository.ClienteRepo;

@Service
public class ClienteService implements ClienteRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private DataSource data;
	

	@Override
	public List<Cliente> allClientes() {
		// List<Cliente> lista = null;
		SimpleJdbcCall sm;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		sm = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_LEER_CLIENTES")
				.returningResultSet("OUT_CLIENTES_CURSOR", BeanPropertyRowMapper.newInstance(Cliente.class));

		Map m = sm.execute(new HashMap<String, Object>(0));
		return (List) m.get("OUT_CLIENTES_CURSOR");
	}

	@Override
	public String obtenerCliente(int id) {

		String cliente = "";
		String query = "SELECT C.NOMBRE_CLIENTE as CLIENTE FROM USUARIO U JOIN CLIENTE C ON C.ID_CLIENTE = U.CLIENTE_ID_CLIENTE WHERE U.ID_USUARIO = "
				+ id + "";
		try (Connection con = jdbcTemplate.getDataSource().getConnection();) {
			PreparedStatement p = con.prepareStatement(query);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				cliente = rs.getString("CLIENTE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

	@Override
	public Cliente agregarCliente(Cliente cliente) {
		Cliente c = new Cliente();

		c.setId_cliente(cliente.getId_cliente());
		c.setRut_cliente(cliente.getRut_cliente());
		c.setNombre_cliente(cliente.getNombre_cliente());

		jdbcTemplate.update("call SP_AGREGA_CLIENTE(?,?)", c.getRut_cliente(), c.getNombre_cliente());

		try {
			int id = 0;
			String sql = "SELECT MAX(id_cliente) as id_cliente FROM CLIENTE";
			data = jdbcTemplate.getDataSource();
			PreparedStatement p = data.getConnection().prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
				c.setId_cliente(id);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return c;
	}

	@Override
	public Cliente actualizarCliente(Cliente cliente) {
		Cliente c = new Cliente();

		c.setId_cliente(cliente.getId_cliente());
		c.setRut_cliente(cliente.getRut_cliente());
		c.setNombre_cliente(cliente.getNombre_cliente());

		try (Connection con = jdbcTemplate.getDataSource().getConnection();) {

			jdbcTemplate.update("call SP_ACTUALIZA_CLIENTE(?,?,?)", c.getId_cliente(), c.getRut_cliente(),
					c.getNombre_cliente());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void eliminarCliente(int id) {
		jdbcTemplate.update("call SP_ELIMINA_CLIENTE(?)", id);
	}

}
