package com.bezkoder.springjwt.security.services;

import java.util.List;

import com.bezkoder.springjwt.models.vehiculos;

public interface IVehiculoService {
	
	public List<vehiculos> findAll();
	
	public vehiculos findById(Long id);
	
	public vehiculos save(vehiculos vehiculo);
	
	public void delete(Long id);
 
}
