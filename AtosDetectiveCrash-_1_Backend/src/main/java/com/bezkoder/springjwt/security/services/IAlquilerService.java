package com.bezkoder.springjwt.security.services;

import java.util.List;
import com.bezkoder.springjwt.models.Alquiler;

public interface IAlquilerService {
	
	public List<Alquiler> findAll();
	
	public Alquiler findById(Long id);
	
	public Alquiler save(Alquiler alquiler);
	
	public void delete(Long id);
}
