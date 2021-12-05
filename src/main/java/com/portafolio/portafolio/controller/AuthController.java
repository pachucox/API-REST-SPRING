package com.portafolio.portafolio.controller;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.portafolio.portafolio.model.Usuario;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@RequestMapping("/login")
public class AuthController {
	

	@Autowired
	JdbcTemplate jdbcTemplate;
	Connection con;

	  @CrossOrigin(origins =  "http://localhost:3000")
	  @PostMapping()
	  public @ResponseBody
	    ResponseEntity<Map<String,Object>> addusuario(@RequestBody Usuario usuario) {
		  		Map<String, Object> model = new HashMap<>();
	            int id_usuario= 0;
	            int perfil_id_perfil=0;
	            
	            String nombre_usuario = "";
	            try (Connection con = jdbcTemplate.getDataSource().getConnection();){
	              String sql = "SELECT * FROM USUARIO WHERE email_usuario = ? AND pass_usuario = ?";
	              PreparedStatement p = con.prepareStatement(sql);
	              p.setString(1,usuario.getEmail_usuario());
	              p.setString(2,usuario.getPass_usuario());
	                  ResultSet rs = p.executeQuery();
	                  while(rs.next()) {
	                  if (rs.getString("email_usuario").equalsIgnoreCase(usuario.getEmail_usuario()) && 
	                      rs.getString("pass_usuario").equalsIgnoreCase(usuario.getPass_usuario())) {
	                  id_usuario= rs.getInt("id_usuario");
	                  
	                  nombre_usuario = rs.getString("nombre_usuario");
	                  perfil_id_perfil = rs.getInt("perfil_id_perfil");
	                  model.put("id",id_usuario);
	                  model.put("nombre",nombre_usuario);
	                  model.put("prioridad",perfil_id_perfil);
	                }
	                  }
	            } catch (Exception e) {
	              return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	            return new ResponseEntity<>(model,HttpStatus.OK);  

	    }

	  // @CrossOrigin(origins =  "http://localhost:3000")
	  @GetMapping("/admin")
		public boolean autenticacionAdmin(@RequestParam(value="email")String email_usuario, @RequestParam(value="pass")String pass_usuario) {
			boolean paso = false;
			try (Connection con = jdbcTemplate.getDataSource().getConnection();){
				String sql = "SELECT * FROM USUARIO WHERE email_usuario = ? AND pass_usuario = ? AND perfil_id_perfil= 1";
				PreparedStatement p = con.prepareStatement(sql);
				p.setString(1, email_usuario);
				p.setString(2,pass_usuario);
		        ResultSet rs = p.executeQuery();
		        while(rs.next()) {
		        	if (rs.getString("email_usuario").equalsIgnoreCase(email_usuario) && rs.getString("pass_usuario").equalsIgnoreCase(pass_usuario)) {
						paso = true;
					}
		        }
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return paso;	
		}


}
