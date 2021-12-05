package com.portafolio.portafolio.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.portafolio.portafolio.model.Departamento;

@Repository
public interface DepartamentoRepo {
	
	 
	 public List<Departamento> allDeptos();
	 //public String obtenerDepartamento(int id);
	 public Departamento agregarDepto(Departamento depto);
	 public Departamento actualizarDepto(Departamento depto);
	 public void eliminarDepto(int id);


}
